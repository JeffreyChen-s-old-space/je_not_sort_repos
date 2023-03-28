import socket

class MainThread_Socket_Client:

    def __init__(self,Port):
        self.Socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.Host = socket.gethostname()
        self.Port = Port

    def Connect(self):
        self.Socket.connect((self.Host,self.Port))
        print(self.Socket.recv(1024))
        self.Socket.close()