package com.example.administrator.httpcommunication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity
{

    private TextView mTextView;
    private SocketClient mSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textview);
        mSocketClient = new SocketClient();
    }

    public void findit(View view) throws Exception
    {
        //        translateMyWordByGet();
        //        translateMyWordByHttpGet();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                socketTest();
            }
        }).start();
    }

    private void socketTest()
    {
        while (mSocketClient.getSocket() == null || !mSocketClient.getSocket().isConnected())
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        mSocketClient.sendAndRcv("client: i come from client");
    }

    public void translateMyWordByHttpGet()
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected String doInBackground(Object[] params)
            {
                try
                {
                    TranslateMyWordByHttpGet translateMyWordByHttpGet = new TranslateMyWordByHttpGet(params[0].toString());
                    return translateMyWordByHttpGet.getByHttpGet();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o)
            {
                mTextView.setText(o.toString());
            }
        };
        String url2find = "http://fanyi.youdao.com/openapi.do?keyfrom=findtrth&key=678238549&type=data&doctype=json&version=1.1&q=good";
        asyncTask.execute(url2find);
    }

    private void translateMyWordByGet()
    {
        TranslateMyWordByUrlConnectionGet translateMyWordByUrlConnectionGet = new TranslateMyWordByUrlConnectionGet();
        try
        {
            translateMyWordByUrlConnectionGet.getByUrlConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mSocketClient.close();
    }
}
