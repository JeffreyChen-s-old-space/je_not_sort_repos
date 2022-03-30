using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Cshape_Socket
{
    class BlockingSocket
    {
        private TcpClient Client;
        private StreamReader streamReader;
        private StreamWriter streamWriter;

        public BlockingSocket(String host,int port)
        {
            Client = new TcpClient();
            Client.Connect(host, port);
            NetworkStream networkStream = new NetworkStream(Client.Client);
            streamReader = new StreamReader(networkStream);
            streamWriter = new StreamWriter(networkStream);

        }

        public void Sendmessage(String Message)
        {
            streamWriter.WriteLine(Message);
            streamWriter.Flush();
            Console.WriteLine(Recieve());
        }

        public String Recieve()
        {
            return streamReader.ReadLine();
        }

        public void Close()
        {
            Client.Close();
        }
    }
}
