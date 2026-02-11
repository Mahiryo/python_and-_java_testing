from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from pydantic import BaseModel
from datetime import datetime
from collections import deque

app = FastAPI()
templates = Jinja2Templates(directory="templates")

SAMPLES = deque(maxlen=500)

class MetricPayload(BaseModel):
    hostname: str
    cpu_percent: float
    mem_percent: float
    disk_percent: float
    timestamp: str  
    gpu_total_memory: str
    gpu_used_memory: str
    gpu_load: str

@app.post("/api/metrics")
def ingest_metrics(payload: MetricPayload):
    SAMPLES.append(payload.model_dump())
    return {"ok": True}

@app.get("/", response_class=HTMLResponse)
def dashboard(request: Request):
    latest = SAMPLES[-1] if SAMPLES else None
    return templates.TemplateResponse(
        "dashboard.html",
        {"request": request, "latest": latest, "count": len(SAMPLES)}
    )

@app.get("/api/latest")
def latest():
    return SAMPLES[-1] if SAMPLES else {}
