import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	public static void main(String[] args)
	{
		ServerSocket serverSocket;
		try
		{
			System.out.println("server is starting\r\n");
			serverSocket = new ServerSocket(12345);
			while (true)
			{
				System.out.println("server is waitting for accepting client\r\n");
				Socket socket = serverSocket.accept();
				socket.setTcpNoDelay(true);
				
				Runnable runnable2read = new ReadRunnable(socket);
				new Thread(runnable2read).start();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
