package com.igor.utils;

public class MoneyUtil {
    public static String clearTextForConverting(String text){
        return text.replaceAll("[ ]", "")
                .replaceAll("[,]", ".");
    }
}
