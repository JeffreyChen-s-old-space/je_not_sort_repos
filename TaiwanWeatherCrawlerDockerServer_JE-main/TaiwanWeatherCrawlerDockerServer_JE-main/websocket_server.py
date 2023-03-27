import sys

from je_websocket import websocket_server

from taiwan_weather_crawler.resource import create_database
from taiwan_weather_crawler.resource import get_crawler_F_C0032_005_data
from taiwan_weather_crawler.resource import get_crawler_W_C0033_001_data
from taiwan_weather_crawler.resource import scheduler
from taiwan_weather_crawler.resource import sql_hazard_weather
from taiwan_weather_crawler.resource import sql_one_weak_weather

job = scheduler.add_job(get_crawler_F_C0032_005_data, "interval", minutes=60)
job1 = scheduler.add_job(get_crawler_W_C0033_001_data, "interval", minutes=60)
create_database()
get_crawler_F_C0032_005_data()
get_crawler_W_C0033_001_data()
scheduler.start()


async def pre_process(websocket, message):
    process_string = message.split(" ")
    print("processed message", process_string)

    if process_string[0] == "select":
        if process_string[1] == "one_week_weather":
            result = sql_one_weak_weather.select_where('LocationName', process_string[2])
            for i in range(len(result)):
                await websocket.send(str(result[i]))
        elif process_string[1] == "hazard_weather":
            result = sql_hazard_weather.select_where('LocationName', process_string[2])
            for i in range(len(result)):
                await websocket.send(str(result[i]))

    elif process_string[0] == "selectAll":
        if process_string[1] == "one_week_weather":
            result = sql_one_weak_weather.select_form()
            for i in range(len(result)):
                await websocket.send(str(result[i]))
            await websocket.send("data done")
        elif process_string[1] == "hazard_weather":
            result = sql_hazard_weather.select_form()
            for i in range(len(result)):
                await websocket.send(str(result[i]))
            await websocket.send("data done")

    elif process_string[0] == "exit":
        print("Connection is closed", websocket, sep="\t")
        await websocket.close()

    elif process_string[0] == "ping":
        await websocket.send("pong")
    else:
        print("Unknown command", process_string, file=sys.stderr)


server = websocket_server("websocket_server", 30003, pre_process, pre_process=True)
