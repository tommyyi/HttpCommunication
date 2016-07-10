package com.example.administrator.httpcommunication;

import android.content.Entity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByHttpPost
{
    private final HttpClient mHttpClient;
    private final HttpPost mHttpPost;
    private String formUrl = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";
    private String formData = "date=2016-7-10&fromstation=VNP&tostation=SHH&starttime=00:00--24:00";
    private HttpResponse mHttpResponse;

    public TranslateMyWordByHttpPost() throws Exception
    {
        mHttpClient = new DefaultHttpClient();
        mHttpPost = new HttpPost(formUrl);
        mHttpPost.setEntity(new StringEntity(formData));
    }

    public String getByHttpPost()
    {
        try
        {
            mHttpResponse = mHttpClient.execute(mHttpPost);
            HttpEntity httpEntity = mHttpResponse.getEntity();
            String entity = EntityUtils.toString(httpEntity);
            Log.e("log", entity);
            System.out.print(entity + "\r\n");
            return entity;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
