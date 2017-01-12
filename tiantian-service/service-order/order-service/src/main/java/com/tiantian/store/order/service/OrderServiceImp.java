package com.tiantian.store.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.core.beans.PageResult;
import com.tiantian.order.apis.OrderService;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.order.vo.OrderVo;
import com.tiantian.store.order.dao.OrderDao;
import com.tiantian.store.order.pojo.Order;

@Service("orderService")
public class OrderServiceImp implements OrderService  {

	@Autowired
	private OrderDao orderDao;
	@Override
	public void createOrder(OrderBo order) {
		
	}

	@Override
	public OrderVo queryOrderById(String orderId) {
		return null;
	}

	@Override
	public PageResult<OrderVo> queryOrderByUserNameAndPage(String buyerNick,
			Integer page, Integer count) {
		return null;
	}

	@Override
	public void changeOrderStatus(OrderBo orderBo) {
		Order order=new Order();
		order.setUpdateTime(new Date());
		this.orderDao.update(order);
		
	}

}
