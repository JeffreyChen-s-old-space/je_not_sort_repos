import asyncio

import websockets


class websocket_server(object):

    def __init__(self, address, port, setCommands, pre_process=False):
        self.Server = websockets.serve(self.message, address, port)
        self.Commands = setCommands
        self.pre_process = pre_process
        self.Users = set()
        asyncio.get_event_loop().run_until_complete(self.Server)
        print("Server start")
        asyncio.get_event_loop().run_forever()

    async def message(self, websocketConnect, path):
        async for message in websocketConnect:
            print("Command : " + message)
            if self.pre_process is False:
                if self.Commands.get(message) is None:
                    print("Unknown command", message, sep="\t")
                else:
                    await self.Commands.get(message)(websocketConnect)
            else:
                await self.Commands(websocketConnect, message)

    async def notify_state(self):
        if self.Users:
            message = state_event()
            await asyncio.wait([user.send(message) for user in self.Users])

    async def notify_users(self):
        if self.Users:
            message = users_event()
            await asyncio.wait([user.send(message) for user in self.Users])

    async def register(self, websocket):
        self.Users.add(websocket)
        await notify_users()

    async def unregister(self, websocket):
        self.Users.remove(websocket)
        await notify_users()
