import socket
from Module.MultiThread_Socket_Server import MultiThread_Socket_Server

Socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
Socket.bind(("localhost", 5555))
Socket.listen(5)



if __name__  == "__main__":
    while True:
        (Client,Address) = Socket.accept()
        MultiThread_Socket_Server(Client,Address).start()