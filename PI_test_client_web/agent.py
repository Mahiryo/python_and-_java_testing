import psutil
import csv
import time
from datetime import date, timedelta, datetime

CSV_FILE = "system_metrics.csv"
DeltaTime = timedelta(days=0, seconds=30)
TEMPO = 2

def collect_metrics():

    cpu = psutil.cpu_percent(interval=0.2)
    mem = psutil.virtual_memory().percent
    disk = psutil.disk_usage("/").percent
    data = datetime.now().isoformat()
    return {
        "timestamp": data,
        "cpu_percent": cpu,
        "mem_percent": mem,
        "disk_percent": disk,
    }

def cleaner():
    payload = collect_metrics()
    c = open(CSV_FILE, mode='w', newline='')
    CSV_cleaner = csv.DictWriter(c, fieldnames=payload.keys(), delimiter=';')
    CSV_cleaner.writeheader()



def main():
    while True: 
        payload = collect_metrics()
        current_timestamp = datetime.now()


        f = open(CSV_FILE, 'a', newline='')
        writer = csv.DictWriter(f, fieldnames=payload.keys(), delimiter=';')
        writer.writerow(payload)
            

        r = open(CSV_FILE, 'r', newline='')
        reader = csv.DictReader(r, delimiter=';')
        row = next(reader, None)
        if row:

            diference = current_timestamp - datetime.fromisoformat(row['timestamp'])

            print(diference)

            if(diference >= DeltaTime):

                cleaner()
                
        time.sleep(TEMPO)
if __name__ == "__main__":
    cleaner()
    main()

