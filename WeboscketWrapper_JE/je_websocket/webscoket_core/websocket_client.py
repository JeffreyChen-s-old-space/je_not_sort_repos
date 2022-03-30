import sys

import websocket

try:
    import thread
except ImportError:
    import _thread as thread


class websocket_client(object):

    def __init__(self, serverURL, setCommands):
        self.websocketClient = websocket.WebSocketApp(serverURL,
                                                      on_open=self.on_open,
                                                      on_message=self.on_message,
                                                      on_error=self.on_error,
                                                      on_close=self.on_close)
        self.Commands = setCommands
        self.websocketClient.run_forever()

    def on_message(self, connect_websocket, message):
        if self.Commands.get(message) is None:
            print("Unknown command", message, sep="\t")
        elif self.Commands.get(message) is not None:
            self.Commands.get(message)(self.websocketClient)
        else:
            print("Unknown", file=sys.stderr)

    def on_error(self, connect_websocket, error):
        print(error)

    def on_close(self, connect_websocket):
        print("### closed ###")

    def on_open(self, connect_websocket):
        print("websocket client open")
        self.websocketClient.send("select 高雄市")
