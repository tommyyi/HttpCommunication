import java.io.IOException;
import java.net.Socket;

public class HeartRunnable implements Runnable
{
	public static final int INTERNAL=10000;
	private Socket mSocket;

	public HeartRunnable(Socket socket)
	{
		mSocket=socket;
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				mSocket.sendUrgentData(0);
				Thread.sleep(INTERNAL);
			} 
		} catch (Exception e)
		{
			try
			{
				mSocket.close();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
