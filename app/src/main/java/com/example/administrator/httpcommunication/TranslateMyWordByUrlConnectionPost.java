package com.example.administrator.httpcommunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByUrlConnectionPost
{
    private String formUrl = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";
    private String formData = "date=2016-7-10&fromstation=VNP&tostation=SHH&starttime=00:00--24:00";

    public void postByUrlConnection() throws Exception
    {
        URL url = new URL(formUrl);
        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        byte[] buffer = new byte[8192];

        File file = new File("aaa");
        if (!file.exists())
        {
            createFile(file);
        }

        postFile(urlConnection, buffer, file);
        getDataFromPost(urlConnection);

        urlConnection.disconnect();
    }

    private void getDataFromPost(HttpURLConnection urlConnection) throws IOException
    {
        final InputStream inputStream = urlConnection.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = "";
        do
        {
            line = bufferedReader.readLine();
            if (line != null)
            {
                System.out.print(line + "\r\n");
            }
        }
        while (line != null);

        bufferedReader.close();
        reader.close();
        inputStream.close();
    }

    private void postFile(HttpURLConnection urlConnection, byte[] buffer, File file) throws IOException
    {
        int length;
        FileInputStream fileInputStream = new FileInputStream(file);
        do
        {
            length = fileInputStream.read(buffer, 0, 8192);
            if (length == -1)
            {
                break;
            }
            urlConnection.getOutputStream().write(buffer, 0, length);
        }
        while (true);
        urlConnection.getOutputStream().flush();
    }

    private void createFile(File file) throws IOException
    {
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Writer fileoutputWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter fileoutputBufferedWriter = new BufferedWriter(fileoutputWriter);
        fileoutputBufferedWriter.write(formData);
        fileoutputBufferedWriter.flush();
        fileoutputBufferedWriter.close();
        fileoutputWriter.close();
        fileOutputStream.close();
    }
}
