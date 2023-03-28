import socketserver

from Module.SocketServer_Server import TCP_SocketServer_Server

with socketserver.TCPServer(("localhost", 5555), TCP_SocketServer_Server) as Server:
    Server.serve_forever()