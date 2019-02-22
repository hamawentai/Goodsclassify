package com.lab.server_search.util;

import java.util.Date;
import java.util.UUID;

public class RowKeyUtil {

    public Integer createRowKey() {

        String str = UUID.randomUUID().toString();
        //去掉产生随机数中的-
        String str1 = str.replace("-","");
        System.out.println(str1);

        return 1;
    }

    public static void main(String[] args) {
        new RowKeyUtil().createRowKey();
        long res = System.currentTimeMillis();

        System.out.println(res);
    }
}
