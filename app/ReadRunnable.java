import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

final class ReadRunnable implements Runnable
{
	Socket localSocket;
	private static InputStream inputStream;
	private static Reader reader;
	private static BufferedReader bufferedReader;
	private static OutputStream outputStream;
	private static Writer writer;
	private static BufferedWriter bufferedWriter;

	public ReadRunnable(Socket localSocket)
	{
		super();
		this.localSocket = localSocket;
		try
		{
			inputStream = localSocket.getInputStream();
			reader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(reader);
			outputStream = localSocket.getOutputStream();
			writer = new OutputStreamWriter(outputStream, "utf-8");
			bufferedWriter = new BufferedWriter(writer);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		try
		{
			while (true)
			{
				System.out.println(Thread.currentThread().getName() + ": checking reader\r\n");
				while(!bufferedReader.ready())
					Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + ": blocking from reading\r\n");
				
				String line = bufferedReader.readLine();
				if(line!=null)
				{
					System.out.println(Thread.currentThread().getName() + ": "+line+"\r\n");
					System.out.println(Thread.currentThread().getName() + ": reading over\r\n");
					
					String mString="Server: i have receive your message\n";
					System.out.println(Thread.currentThread().getName() + ": writing preparing\r\n");
					//outputStream.write(mString.getBytes());//it also works
					bufferedWriter.write(mString);
					bufferedWriter.flush();
					System.out.println(Thread.currentThread().getName() + ": writing over\r\n");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}