using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Cshape_Socket
{
    class SocketClient
    {
        private TcpClient tcpClient;
        private NetworkStream networkStream;

        public SocketClient(String host,int port)
        {
            tcpClient = new TcpClient();
            tcpClient.Connect(host, port);
            networkStream = tcpClient.GetStream();
        }

        public void SendMessage(String message)
        {
            byte[] outPut= Encoding.ASCII.GetBytes(message);
            networkStream.Write(outPut,0,outPut.Length);
            networkStream.Flush();

            byte[] input = new byte[1024];
            networkStream.Read(input, 0, input.Length);
            String receiveMessage = Encoding.ASCII.GetString(input);
            Console.WriteLine(receiveMessage);
        }

        public void Close()
        {
            tcpClient.Close();
        }

    }
}
