using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SocketServer
{
    class Test
    {
        static void Main(string[] args)
        {
            SocketServer socketServer = new SocketServer(5555);
            socketServer.Start_Server();
        }
    }
}
