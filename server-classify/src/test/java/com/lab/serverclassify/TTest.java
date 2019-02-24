package com.lab.serverclassify;


import com.lab.serverclassify.utils.TimeUtils;
import org.junit.Test;

public class TTest {

    @Test
    public void timeTest(){
        String[] date = TimeUtils.getNowTime().split("\\|");
        System.out.println(date[0]+" "+date[1]);
    }
}
