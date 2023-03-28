from Module.MainThread_Socket_Server import MainThread_Socket_Server
Server = MainThread_Socket_Server(5555)
Server.Set_Listen(5)
Server.Run()

