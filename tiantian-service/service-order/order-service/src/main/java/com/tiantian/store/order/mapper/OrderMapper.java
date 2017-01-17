package com.tiantian.store.order.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.pagehelper.PageInfo;
import com.tiantian.core.mapper.plugin.TianTianMapper;
import com.tiantian.store.order.bean.Where;
import com.tiantian.store.order.pojo.Order;



public interface OrderMapper extends  TianTianMapper<Order>{
	
	
	/**
	 * 按照where条件分页查询
	 * 
	 * @param bounds
	 * @param where
	 * @return
	 */
	public PageInfo<Order> queryListByWhere(PageBounds bounds, @Param("where")Where where);
	/**
	 * 通过where条件查询
	 * 
	 * @param where
	 * @return
	 */
	public  Order queryByWhere(@Param("where") Where where);
	
	
	public void paymentOrderScan(@Param("date") Date date);
	/**
	 * 通过ID查询数据
	 * 
	 * @param id
	 * @return
	 */
	public Order queryByID(@Param("id") String id);
	
	
	/**
	 * 新增数据
	 * 
	 * @param t
	 */
	public void save(Order t);
	
}
