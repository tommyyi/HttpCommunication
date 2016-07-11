package com.example.administrator.httpcommunication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Administrator on 2016/7/10.
 */
public class SocketClient
{
    public static final int SIZE = 200;
    private String ip = "172.16.109.1";
    private int port = 12345;
    private OutputStream mOutputStream;
    private Socket mSocket;
    private Writer mWriter;
    private BufferedWriter mBufferedWriter;
    private InputStream mInputStream;
    private Reader mReader;
    private BufferedReader mMBufferedReader;

    public Socket getSocket()
    {
        return mSocket;
    }

    public SocketClient()
    {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    mSocket = new Socket(ip, port);
                    mSocket.setTcpNoDelay(true);

                    mOutputStream = mSocket.getOutputStream();
                    mWriter = new OutputStreamWriter(mOutputStream, "utf-8");
                    mBufferedWriter = new BufferedWriter(mWriter,10);

                    mInputStream = mSocket.getInputStream();
                    mReader = new InputStreamReader(mInputStream, "utf-8");
                    mMBufferedReader = new BufferedReader(mReader,10);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void sendAndRcv(final String msg)
    {
        if (mSocket==null||!mSocket.isConnected())
        {
            return;
        }

        Runnable writeRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Log.e("socket", "send ready");
                    mOutputStream.write(msg.getBytes());//one write operation is corresponding to one TCP package
                    mOutputStream.write(msg.getBytes());
                    Log.e("socket", "send over");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        new Thread(writeRunnable).start();

        Runnable readRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    while (mInputStream.available()<=0)
                        Thread.sleep(100);

                    Log.e("socket_reading", "block from reading");
                    byte[] buffer=new byte[SIZE];
                    int count=0;
                    int number=0;
                    while(mInputStream.available()>=100)
                    {
                        number = mInputStream.read(buffer,count,100);
                        count=count+number;
                        Log.e("socket_reading", new String(buffer,0,count,"utf-8"));
                    }
                    number=mInputStream.read(buffer,count,100);
                    count=number+count;
                    String ret=new String(buffer,0,count,"utf-8").replace("\r","");
                    Log.e("socket_reading", ret);
                    Log.e("socket_reading", "reading is over");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        new Thread(readRunnable).start();
    }

    public void close()
    {
        try
        {
            closeReader();
            closeWriter();
            mSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void closeWriter() throws IOException
    {
        mBufferedWriter.close();
        mWriter.close();
        mOutputStream.close();
    }

    private void closeReader() throws IOException
    {
        mInputStream.close();
        mReader.close();
        mMBufferedReader.close();
    }
}
