import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private static final class Runnable2Write implements Runnable
	{
		Socket localSocket;

		public Runnable2Write(Socket localSocket)
		{
			super();
			this.localSocket = localSocket;
		}

		public void run()
		{
			/*try
			{
				handleWrite(localSocket);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

	private static final class ReadRunnable implements Runnable
	{
		Socket localSocket;

		public ReadRunnable(Socket localSocket)
		{
			super();
			this.localSocket = localSocket;
		}

		public void run()
		{
			try
			{
				handleRead(localSocket);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			System.out.println("server is starting\r\n");
			ServerSocket serverSocket = new ServerSocket(12345);
			while (true)
			{
				System.out.println("server is waitting for accepting client\r\n");
				Socket socket = serverSocket.accept();
				socket.setTcpNoDelay(true);
				
				Runnable runnable2read = new ReadRunnable(socket);
				new Thread(runnable2read).start();

//				Runnable2Write runnable2write = new Runnable2Write(socket);
//				new Thread(runnable2write).start();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void handleRead(Socket socket) throws Exception
	{
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		
		while (true)
		{
			System.out.println(Thread.currentThread().getName() + ": block from reading\r\n");

			byte[] buffer=new byte[100];
			int count=0;
			int number=0;
			while(inputStream.available()>=100)
			{
				number = inputStream.read(buffer,count,100);
				count=count+number;
				System.out.println(Thread.currentThread().getName()+": "+new String(buffer,0,count,"utf-8")+"\r\n");
			}
			number=inputStream.read(buffer,count,100);
			count=number+count;
			
			System.out.println(Thread.currentThread().getName()+": "+new String(buffer,0,count,"utf-8")+"\r\n");
			System.out.println(Thread.currentThread().getName() + ": reading over\r\n");
			
			String mString="Server: i have receive your message\r";
			outputStream.write(mString.getBytes());
			System.out.println(Thread.currentThread().getName() + ": writing over\r\n");
		}
	}
}
