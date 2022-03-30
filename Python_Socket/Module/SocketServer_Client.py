import socket

class SocketServer_Client():

    def __init__(self,Host,Port):
        self.Host = Host
        self.Port = Port
        self.Message = "Hello Server"
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as Socket:
            Socket.connect((self.Host, self.Port))
            Socket.sendall(bytes(self.Message + "\n", "UTF-8"))
            Received = str(Socket.recv(1024), "utf-8")
            print("Sent : {}".format(self.Message))
            print("Received : {}".format(Received))