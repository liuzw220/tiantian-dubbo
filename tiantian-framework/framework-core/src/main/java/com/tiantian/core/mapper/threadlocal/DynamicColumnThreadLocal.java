package com.tiantian.core.mapper.threadlocal;
/**
 * 需要查询的字段放到本地线程
 * 
 * @author good-zhiwei 刘志伟
 * @data 2016年7月10日 下午12:52:52
 */
public class DynamicColumnThreadLocal {
    public static final  ThreadLocal<String> THREAD_DYNAMICCOLUMN=new ThreadLocal<String>();
    
    /**
     * 把当前需要执行的sql的列绑定到线程
     * @param user
     */
    public static void set(String dynamicColumn){
        THREAD_DYNAMICCOLUMN.set(dynamicColumn);
    }
    /**
     * 获取当前需要执行sql的列
     * @param user
     */
    public static String get(){
       return THREAD_DYNAMICCOLUMN.get();
    }
    /**
     * 清除当前线程
     */
    public static void clear(){
        THREAD_DYNAMICCOLUMN.set(null);
    }
}
