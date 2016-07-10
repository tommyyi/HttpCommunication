package com.example.administrator.httpcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByUrlConnectionGet
{
    String url2find = "http://fanyi.youdao.com/openapi.do?keyfrom=findtrth&key=678238549&type=data&doctype=json&version=1.1&q=good";

    public void getByUrlConnection() throws Exception
    {
        URL url=new URL(url2find);
        final URLConnection urlConnection = url.openConnection();

        getDataFromGet(urlConnection);
    }

    private void getDataFromGet(URLConnection urlConnection) throws IOException
    {
        final InputStream inputStream = urlConnection.getInputStream();
        Reader reader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(reader);

        String line="";
        do
        {
            line=bufferedReader.readLine();
            if(line!=null)
                System.out.print(line+"\r\n");
        }
        while (line!=null);

        bufferedReader.close();
        reader.close();
        inputStream.close();
    }
}
