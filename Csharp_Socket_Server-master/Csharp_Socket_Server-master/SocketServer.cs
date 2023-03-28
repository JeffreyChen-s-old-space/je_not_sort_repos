using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SocketServer
{

    class SocketServer
    {

        private TcpListener tcpListener;
        private TcpClient tcpClient;
        private int connectCount;
        private bool Running = true;

        public SocketServer(int port)
        {
            tcpListener = new TcpListener(port);
            tcpClient = default(TcpClient);
            tcpListener.Start();
            Console.WriteLine("Server Start");
            connectCount = 0;
            this.Start_Server();
        }

        public void Start_Server()
        {
            while (Running)
            {
                connectCount += 1;
                tcpClient = tcpListener.AcceptTcpClient();
                HandleClient handleClient = new HandleClient();
                handleClient.StartClient(tcpClient, Convert.ToString(connectCount));
            }
            tcpClient.Close();
            tcpListener.Stop();
            Console.WriteLine("Exit");
            Console.ReadLine();
        }

        public class HandleClient{
            TcpClient client;
            String clientNo;
            bool Running=true;

            public void StartClient(TcpClient tcpClient,String clientNo)
            {
                this.client = tcpClient;
                this.clientNo = clientNo;
                Thread thread = new Thread(ClientConnect);
                thread.Start();
            }

            private void ClientConnect()
            {
                int requestCount = 0;
                byte[] buffer = new byte[1024];
                String clientMessage = null;
                Byte[] sendByte = null;
                String serverResponse = null;
                while (Running)
                {
                    requestCount += 1;
                    NetworkStream networkStream = client.GetStream();
                    networkStream.Read(buffer, 0, buffer.Length);
                    clientMessage = Encoding.ASCII.GetString(buffer);
                    Console.WriteLine("Client send : " + clientMessage);
                    serverResponse = "Hello Client";
                    sendByte = Encoding.ASCII.GetBytes(serverResponse);
                    networkStream.Write(sendByte, 0, sendByte.Length);
                    networkStream.Flush();
                }
            }
        }
    }
}
