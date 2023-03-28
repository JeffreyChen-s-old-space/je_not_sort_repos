import socket
import threading

class MultiThread_Socket_Server(threading.Thread):

    def __init__(self,Socket,Address):
        super().__init__()
        self.Socket = Socket
        self.Address = Address
        self.Message = "Hello I'm Server"
        self.Lock = threading.Lock()


    def Set_Message(self,Message):
        self.Message = Message

    def run(self) -> None:
        while True:
            try:
                Data = self.Socket.recv(1024)
                if not Data:
                    break
                self.Socket.send(str(self.Message).encode("utf-8"))
            except Exception as Errr:
                print(Errr)
        self.Socket.close()


