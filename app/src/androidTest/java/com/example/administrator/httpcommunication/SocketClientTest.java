package com.example.administrator.httpcommunication;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/10.
 */
public class SocketClientTest
{
    @Test
    public void testConnect() throws Exception
    {
        SocketClient socketClient=new SocketClient();
        socketClient.connect();
        while (socketClient.getSocket()==null||!socketClient.getSocket().isConnected())
        {
            Thread.sleep(1000);
        }
        socketClient.sendAndRcv("client: i come from client\n");
        Thread.sleep(1000000);
    }
}