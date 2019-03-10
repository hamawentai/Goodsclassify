package com.lab.serverclassify;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TTest {

    @Test
    public void timeTest() throws Exception {
        long t1 = System.currentTimeMillis();
        String str = String.valueOf(t1);
        Thread.sleep(100);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(str.length());
        System.out.println(format.format(new Date(t1)));
        System.out.println(System.currentTimeMillis() - t1);
    }
}
