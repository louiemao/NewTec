package com.sws.newtec.modules.common.utils;

import java.util.Date;

/**
 * Created by george on 15-6-29.
 */
public  class  DateUtil {
    /**
     *
     * @return返回当前的时间对象
     */
    public static Date now(){
        try {
            return Date.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
