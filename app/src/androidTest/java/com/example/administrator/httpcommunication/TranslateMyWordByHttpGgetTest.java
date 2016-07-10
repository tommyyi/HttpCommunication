package com.example.administrator.httpcommunication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/10.
 */
public class TranslateMyWordByHttpGgetTest
{

    @Test
    public void testGetByHttpGet() throws Exception
    {
        String url2find = "http://fanyi.youdao.com/openapi.do?keyfrom=findtrth&key=678238549&type=data&doctype=json&version=1.1&q=good";

        TranslateMyWordByHttpGet translateMyWordByHttpGet=new TranslateMyWordByHttpGet(url2find);
        translateMyWordByHttpGet.getByHttpGet();
    }
}