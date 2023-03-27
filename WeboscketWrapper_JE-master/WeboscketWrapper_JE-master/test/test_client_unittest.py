import unittest

from je_websocket import websocket_client


def receive_f(connect_websocket):
    print("f")
    connect_websocket.close()


class TestWebsocketClient(unittest.TestCase):

    def test(self):
        commands = {"f": receive_f}
        client = websocket_client('ws://localhost:5555', commands)


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestWebsocketClient))
    unittest.TextTestRunner(verbosity=2).run(suite)
