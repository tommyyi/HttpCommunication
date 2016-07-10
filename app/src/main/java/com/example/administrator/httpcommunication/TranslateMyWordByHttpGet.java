package com.example.administrator.httpcommunication;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByHttpGet
{
    private final HttpClient mHttpClient;
    private final HttpGet mHttpGet;
    private HttpResponse mHttpResponse;

    public TranslateMyWordByHttpGet(String url2find) throws Exception
    {
        mHttpClient = new DefaultHttpClient();
        mHttpGet = new HttpGet(url2find);
    }

    public String getByHttpGet()
    {
        try
        {
            mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity httpEntity = mHttpResponse.getEntity();
            String entity = EntityUtils.toString(httpEntity);
            Log.e("log",entity);
            System.out.print(entity+"\r\n");
            return entity;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
