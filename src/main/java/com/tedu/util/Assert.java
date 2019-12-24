package com.tedu.util;

import com.tedu.pojo.JsonResult;

public class Assert {
    /**
     * 如果object是null 抛出异常
     * @param object object
     * @return false/true
     */
    public static boolean isNull(Object object){
//        if (object == null ) throw new RuntimeException(message);
        return object == null;
    }

    public static boolean isNull(Number size){
        return isNull((Object)size) && size.doubleValue()<1;
    }

    public static void isValid(Boolean flag,String message){
        if (flag) throw new RuntimeException(message);
    }


}
