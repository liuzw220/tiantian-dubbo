package com.tiantain.common.service;
/**
 * 
 * @author schoolBoy 刘志伟
 * @data 2015年5月18日 下午1:17:05
 * @param <E> 传入对象
 * @param <T> 返回对象
 */
public interface IRedisFunction<E,T> {
    /**
     * 传入一个对象，执行具体业务，再返回一个对象
     * @param e 传入对象
     * @return 返回对象
     */
    public  T callBack(E e);
}
