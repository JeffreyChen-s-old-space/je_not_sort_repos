from je_websocket import websocket_server


async def pre_process(websocket, message):
    if message == "print_f":
        await websocket.send("f")
    else:
        await websocket.send("Unknown")


server = websocket_server("localhost", 5555, pre_process, pre_process=True)
