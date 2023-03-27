from je_websocket import websocket_client


def receive_f(connect_websocket):
    print("f")
    connect_websocket.close()


commands = {"f": receive_f}

client = websocket_client("ws://localhost:30001", commands)
