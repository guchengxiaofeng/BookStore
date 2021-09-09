package com.zxc.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zhu
 * @create 2021-08-23 14:06
 */
public class WebUtils {

   public static <T> T copyParamToBean(Map value, T bean){
       try {

           BeanUtils.populate(bean,value);

       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
        return bean;
   }
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
