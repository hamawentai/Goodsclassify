package com.lab.server_search.test;

import com.lab.server_search.myEnum.SearchEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EunmTest {

    @Test
    public void test() {
        System.out.println(SearchEnum.COMMODITY_SEARCH.getType());
        boolean res = "1".equals(SearchEnum.COMMODITY_SEARCH.getType());
        System.out.println(res);
    }

    @Test
    public void list() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        boolean contains = list.contains("1");
        System.out.println(contains);
    }
}
