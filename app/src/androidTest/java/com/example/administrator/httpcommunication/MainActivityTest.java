package com.example.administrator.httpcommunication;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/10.
 */
public class MainActivityTest
{
    @Test
    public void testTranslateMyWordByHttpGet() throws Exception
    {
        String url2find = "http://fanyi.youdao.com/openapi.do?keyfrom=findtrth&key=678238549&type=data&doctype=json&version=1.1&q=good";

        TranslateMyWordByHttpGet translateMyWordByHttpGet=new TranslateMyWordByHttpGet(url2find);
        translateMyWordByHttpGet.getByHttpGet();
    }
}