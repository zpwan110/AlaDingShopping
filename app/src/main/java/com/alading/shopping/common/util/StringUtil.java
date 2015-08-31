package com.alading.shopping.common.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/8/29.
 */
public class StringUtil {
    public static boolean isNumeric(String str){
        if(str.length()>0){
            for (int i = str.length();--i>=0;){
                if (!Character.isDigit(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
