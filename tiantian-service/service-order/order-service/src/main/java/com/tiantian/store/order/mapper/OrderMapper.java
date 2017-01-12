package com.tiantian.store.order.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
	public PageList<Order> queryListByWhere(PageBounds bounds, @Param("where")Where where);
	/**
	 * 通过where条件查询
	 * 
	 * @param where
	 * @return
	 */
	public  Order queryByWhere(@Param("where") Where where);
	
	
	public void paymentOrderScan(@Param("date") Date date);
	
}
