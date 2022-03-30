import datetime

from Module.MainThread_Socket_Server import MainThread_Socket_Server
from Module.MultiThread_Socket_Server import MultiThread_Socket_Server
from Module.SocketServer_Server import TCP_SocketServer_Server
from Module.SocketServer_Server import UDP_SocketServer_Server

class Socket_Server_Core():

    def __init__(self):
        try:
            self.MainThread_Socket_Server=MainThread_Socket_Server
            self.MultiThread_Socket_Server=MultiThread_Socket_Server
            self.TCP_SocketServer_Server=TCP_SocketServer_Server
            self.UDP_SocketServer_Server=UDP_SocketServer_Server
            print(datetime.datetime.now(), self.__class__, ' Ready')
        except Exception as Errr:
            raise(Errr)