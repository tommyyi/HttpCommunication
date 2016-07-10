package com.example.administrator.httpcommunication;

import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest
{
    @Test
    public void testGetByHttpGet() throws Exception
    {
        String url2find = "http://fanyi.youdao.com/openapi.do?keyfrom=findtrth&key=678238549&type=data&doctype=json&version=1.1&q=good";

        TranslateMyWordByHttpGet translateMyWordByHttpGet=new TranslateMyWordByHttpGet(url2find);
        translateMyWordByHttpGet.getByHttpGet();
    }
}