package com.example.administrator.httpcommunication;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByUrlConnectionGetTest
{

    @Test
    public void testTranslate() throws Exception
    {
        TranslateMyWordByUrlConnectionGet translateMyWordByUrlConnectionGet =new TranslateMyWordByUrlConnectionGet();
        translateMyWordByUrlConnectionGet.getByUrlConnection();
    }
}