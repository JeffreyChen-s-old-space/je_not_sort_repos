import unittest

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


class TestServerWebsocket(unittest.TestCase):

    def setUp(self) -> None:
        self.command_dictionary = {"print_f": print_f, "print_z": print_z, "exit_websocket": exit_websocket}

    def test(self):
        with self.assertRaises(SystemExit) as systemExit:
            server = websocket_server("localhost", 5555, self.command_dictionary)
        self.assertEqual(systemExit.exception.code, 0)


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestServerWebsocket))
    unittest.TextTestRunner(verbosity=2).run(suite)
