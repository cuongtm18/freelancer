package com.btl.utils;

public class StringEx {
    public static String Trim(Object oInput) {
        if (oInput == null || oInput.toString().isEmpty())
            return "";
        return oInput.toString().trim();
    }
}
