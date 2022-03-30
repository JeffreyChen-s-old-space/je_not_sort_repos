using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Cshape_Socket
{
    class Test
    {
        static void Main(string[] args)
        {
            SocketClient socketClient = new SocketClient("localhost",5555);
            socketClient.SendMessage("Hello Server I'm C#");
            socketClient.Close();
        }
    }
}
