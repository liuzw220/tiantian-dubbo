package com.tiantian.core.datasource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 定义动态数据源，实现通过集成Spring提供的AbstractRoutingDataSource，只需要实现determineCurrentLookupKey方法即可
 * 
 * 由于DynamicDataSource是单例的，线程不安全的，所以采用ThreadLocal保证线程安全，由DynamicDataSourceHolder完成。<br/> 
 
 * <br/> <b> 一主多从的情况下,会随机的从多个从库中选择一个. 建议一主一从的模式下使用</b> <br/>
 * 
 * @author LiuZhiwei <br/>
 *
 * 2016年10月2日 下午1:11:19<br/>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override  
    protected Object determineCurrentLookupKey() {  
     // 使用DynamicDataSourceHolder保证线程安全，并且得到当前线程中的数据源key  
        return DynamicDataSourceHolder.getDataSourceKey();  
    }  
}
