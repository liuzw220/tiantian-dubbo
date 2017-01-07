package com.tiantian.service.redis;

public interface IRedisFunction<E, T> {

    public T callBack(E e);

}
