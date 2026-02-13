import psutil
import requests

import GPUtil

from datetime import datetime, timezone

SERVER_URL = "http://127.0.0.1:8000/api/metrics"
INTERVAL_SECONDS = 2

def collect_metrics():
    cpu = psutil.cpu_percent(interval=0.2)
    mem = psutil.virtual_memory().percent
    disk = psutil.disk_usage("/").percent

    return {
        "cpu_percent": float(cpu),
        "mem_percent": float(mem),
        "disk_percent": float(disk),
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
