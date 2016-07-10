package com.example.administrator.httpcommunication;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/9.
 */
public class TranslateMyWordByUrlConnectionPostTest
{

    @Test
    public void testPostByUrlConnection() throws Exception
    {
        TranslateMyWordByUrlConnectionPost translateMyWordByUrlConnectionPost =new TranslateMyWordByUrlConnectionPost();
        translateMyWordByUrlConnectionPost.postByUrlConnection();
    }
}