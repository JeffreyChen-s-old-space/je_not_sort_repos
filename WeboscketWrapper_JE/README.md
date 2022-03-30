# JEWebsocket

---

### how to use
* pip install JEWebsocket
* Create dict include command : function key:value
* Init Server with host, port, command dict

```python
from je_websocket import websocket_server


async def printF(websocket):
    print("F")
    await websocket.send("F")


async def printZ(websocket):
    print("Z")
    await websocket.send("Z")


async def exitWebsocket(websocket):
    print("Connection is closed", websocket, sep="\t")
    await websocket.close()


commandDictionary = {"printF": printF, "printZ": printZ, "exitWebsocket": exitWebsocket}
server = websocket_server("websocket_server", 5555, commandDictionary)
``` 

* Now you can use client to control this websocket server
    * client send "printF" to server, server will print "F" and echo "F"
    * client send "printZ" to server, server will print "Z" and echo "Z"
    * client send "exitWebsocket" to server, server will print "Connection is closed"
      & connect and then server will close connect

---