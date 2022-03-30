import socketserver

class TCP_SocketServer_Server(socketserver.StreamRequestHandler):

    def handle(self) -> None:
        Data = self.rfile.readline().strip()
        print("{} wrote".format(self.client_address[0]))
        print(Data)
        self.wfile.write(Data.upper())

class UDP_SocketServer_Server(socketserver.BaseRequestHandler):

    def handle(self) -> None:
        Data = self.request[0].strip()
        Socket = self.request[1]
        print("{} wrote:".format(self.client_address[0]))
        print(Data)
        Socket.sendto(Data.upper(), self.client_address)
