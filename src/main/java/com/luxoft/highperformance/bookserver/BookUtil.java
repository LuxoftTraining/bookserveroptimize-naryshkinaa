package com.luxoft.highperformance.bookserver;

public class BookUtil {

    public static String[] split(String title){
        String[] keywords = title.split(" ");
        return keywords;
    }
}
