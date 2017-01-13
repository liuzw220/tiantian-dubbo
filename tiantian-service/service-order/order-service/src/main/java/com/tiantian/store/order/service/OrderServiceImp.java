package com.tiantian.store.order.service;

import java.util.Date;

import org.dozer.Mapper;
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
	@Autowired
	private Mapper dozerMapper;
	@Override
	public String createOrder(OrderBo orderBo) {
		// 生成订单ID，规则为：userid+当前时间戳
		String orderId = orderBo.getUserId() + "" + System.currentTimeMillis();
		orderBo.setOrderId(orderId);
		// 设置订单的初始状态为未付款
		orderBo.setStatus(1);
		// 设置订单的创建时间
		orderBo.setCreateTime(new Date());
		orderBo.setUpdateTime(orderBo.getCreateTime());
		// 设置买家评价状态，初始为未评价
		orderBo.setBuyerRate(0);
		Order order=dozerMapper.map(orderBo, Order.class);
		orderDao.save(order);
		return orderId;

	}

	@Override
	public OrderVo queryOrderById(String orderId) {
		Order order=orderDao.queryOrderById(orderId);
		return dozerMapper.map(order, OrderVo.class);
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
