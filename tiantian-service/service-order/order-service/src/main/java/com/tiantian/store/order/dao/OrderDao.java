package com.tiantian.store.order.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.pagehelper.PageInfo;
import com.tiantian.core.beans.PageResult;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.store.order.bean.Where;
import com.tiantian.store.order.mapper.OrderMapper;
import com.tiantian.store.order.pojo.Order;
@Service
public class OrderDao extends BaseDao<Order>{

	@Autowired
	private OrderMapper orderMapper;
	public void createOrder(Order order) {
		orderMapper.save(order);
	}
	public Order queryOrderById(String orderId) {
		return orderMapper.queryByID(orderId);
	}
	
	public PageResult<Order> queryOrderByUserNameAndPage(OrderBo orderBo) {
		PageBounds bounds = new PageBounds();
		bounds.setContainsTotalCount(true);
		bounds.setLimit(orderBo.getPageSize());
		bounds.setPage(orderBo.getPageIndex());
		bounds.setOrders(com.github.miemiedev.mybatis.paginator.domain.Order.formString("create_time.desc"));
		PageInfo<Order> pageInfo = this.orderMapper.queryListByWhere(bounds, Where.build("buyer_nick", orderBo.getBuyerNick()));
		return new PageResult<Order>(pageInfo.getTotal(), pageInfo.getList());
	}
	
	public void changeOrderStatus(Order order) {
		try {
			order.setUpdateTime(new Date());
			super.update(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void paymentOrderScan(Date date) {
		orderMapper.paymentOrderScan(date);
	}

}
