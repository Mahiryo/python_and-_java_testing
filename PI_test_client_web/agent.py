import time
import socket
import psutil
import requests
import GPUtil
from datetime import datetime, timezone

SERVER_URL = "http://127.0.0.1:8000/api/metrics"
INTERVAL_SECONDS = 2



def collect_metrics():
    gpus = GPUtil.getGPUs()

    cpu = psutil.cpu_percent(interval=0.2)
    mem = psutil.virtual_memory().percent
    disk = psutil.disk_usage("/").percent

    gpu = gpus[1]
    return {
        "hostname": socket.gethostname(),
        "cpu_percent": float(cpu),
        "mem_percent": float(mem),
        "disk_percent": float(disk),
        "timestamp": datetime.now(timezone.utc).isoformat(),
        "gpu_total_memory": str(f"{gpu.memoryTotal}MB"),
        "gpu_used_memory": str(f"{gpu.memoryUsed}MB"),
        "gpu_load": str(f"{gpu.load * 100}%"),
    }

def main():
    while True:
        payload = collect_metrics()
        try:
            r = requests.post(SERVER_URL, json=payload, timeout=3)
            r.raise_for_status()
        except Exception as e:
            print("Send failed:", e)
        time.sleep(INTERVAL_SECONDS)

if __name__ == "__main__":
    main()
