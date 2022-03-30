import socket

class MainThread_Socket_Server:

    def __init__(self,Port):
        self.Socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.Host = socket.gethostname()
        self.Port = Port
        self.Socket.bind((self.Host,self.Port))

    def Set_Listen(self,Listen):
        self.Socket.listen(Listen)

    def Run(self):
        while True:
            connection,addr=self.Socket.accept()
            print("連結IP",addr)
            connection.send("Hello I'm Server".encode("utf-8"))
        connection.close()