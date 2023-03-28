import datetime

from Module.MainThread_Socket_Client import MainThread_Socket_Client
from Module.SocketServer_Client import SocketServer_Client

class Socket_Client_Core():

    def __init__(self):
        try:
            self.MainThread_Socket_Client=MainThread_Socket_Client
            self.SocketServer_Client=SocketServer_Client
            print(datetime.datetime.now(),self.__class__,' Ready')
        except Exception as Errr:
            raise(Errr)