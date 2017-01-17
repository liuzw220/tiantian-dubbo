package com.tiantian.store.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.core.beans.PageResult;
import com.tiantian.order.apis.OrderService;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.order.vo.OrderItemVo;
import com.tiantian.order.vo.OrderVo;
import com.tiantian.store.order.dao.OrderDao;
import com.tiantian.store.order.pojo.Order;
import com.tiantian.store.order.pojo.OrderItem;

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
		OrderVo result=dozerMapper.map(order, OrderVo.class);
		result.setOrderItems(poToVo(order.getOrderItems()));
		return result;
	}

	@Override
	public PageResult<OrderVo> queryOrderByUserNameAndPage(OrderBo orderBo) {
		return null;
	}

	@Override
	public void changeOrderStatus(OrderBo orderBo) {
		Order order=new Order();
		order.setUpdateTime(new Date());
		this.orderDao.update(order);
	}
	
	
	public List<OrderItemVo> poToVo(List<OrderItem> orders){
		List<OrderItemVo> list=new ArrayList<OrderItemVo>();
		for(OrderItem oi:orders){
			list.add(dozerMapper.map(oi, OrderItemVo.class));
		}
		return list;
		
	}

}
