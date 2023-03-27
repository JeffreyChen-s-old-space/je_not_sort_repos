from je_websocket import websocket_server

from taiwan_hotel_crawler.resource import create_database
from taiwan_hotel_crawler.resource import get_crawler_data
from taiwan_hotel_crawler.resource import scheduler
from taiwan_hotel_crawler.resource import sql

job = scheduler.add_job(get_crawler_data, "interval", minutes=60)
create_database()
get_crawler_data()
scheduler.start()


async def pre_process(websocket, message):
    process_string = message.split(" ")
    print("processed message", process_string)

    if process_string[0] == "select":
        result = sql.select_where('Region', process_string[1])
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


server = websocket_server("localhost", 30001, pre_process, pre_process=True)
