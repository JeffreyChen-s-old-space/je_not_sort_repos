from je_websocket import websocket_server

from taiwan_map_marker.resource import create_database
from taiwan_map_marker.resource import marker_sql

create_database()


async def pre_process(websocket, message):
    process_string = message.split(" ")

    if process_string[0] == "select":
        result = marker_sql.select_form()
        for i in range(len(result)):
            await websocket.send(str(result[i]))
        await websocket.send("data done")

    elif process_string[0] == "insert":
        marker_sql.insert_into(None,
                               process_string[1],
                               process_string[2],
                               process_string[3],
                               process_string[4])

    elif process_string[0] == "exit":
        await websocket.close()

    elif process_string[0] == "ping":
        await websocket.send("pong")


server = websocket_server("websocket_server", 30005, pre_process, pre_process=True)
