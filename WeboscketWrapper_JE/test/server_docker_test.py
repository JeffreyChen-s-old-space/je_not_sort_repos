from je_websocket import websocket_server


async def print_f(websocket):
    print("f")
    await websocket.send("f")


async def print_z(websocket):
    print("z")
    await websocket.send("z")


async def exit_websocket(websocket):
    print("Connection is closed", websocket, sep="\t")
    await websocket.close()


command_dictionary = {"print_f": print_f, "print_z": print_z, "exit_websocket": exit_websocket}

server = websocket_server("websocket_server", 5555, command_dictionary)
