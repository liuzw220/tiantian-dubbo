package com.tiantian.item.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiantian.core.beans.PageResult;
import com.tiantian.item.bean.ResultMsg;
import com.tiantian.item.bean.TaotaoResult;
import com.tiantian.order.apis.OrderService;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.order.util.ValidateUtil;
import com.tiantian.order.vo.OrderVo;

@Service
public class OrderServiceBusiness {

	@Autowired
	private OrderService  orderService;

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public TaotaoResult createOrder(String json) {
		OrderBo order = null;
		try {
			order = objectMapper.readValue(json, OrderBo.class);
			// 校验Order对象
			ValidateUtil.validate(order);
		} catch (Exception e) {
			return TaotaoResult.build(400, "请求参数有误!");
		}
		try {
			// 生成订单ID，规则为：userid+当前时间戳
			String orderId = order.getUserId() + "" + System.currentTimeMillis();
			order.setOrderId(orderId);
			// 设置订单的初始状态为未付款
			order.setStatus(1);
			// 设置订单的创建时间
			order.setCreateTime(new Date());
			order.setUpdateTime(order.getCreateTime());
			// 设置买家评价状态，初始为未评价
			order.setBuyerRate(0);
			// 持久化order对象
			orderService.createOrder(order);
			return TaotaoResult.ok(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.build(400, "保存订单失败!");
	}

	public OrderVo queryOrderById(String orderId) {
		OrderVo order = orderService.queryOrderById(orderId);
		return order;
	}

	public PageResult<OrderVo> queryOrderByUserNameAndPage(String buyerNick,
			Integer page, Integer count) {
		return orderService.queryOrderByUserNameAndPage(buyerNick, page, count);
	}

	public ResultMsg changeOrderStatus(String json) {
		OrderBo order = null;
		try {
			order = objectMapper.readValue(json, OrderBo.class);
			this.orderService.changeOrderStatus(order);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg("500", "更新订单出错!");
		}
		return new ResultMsg("200", "更新成功!");
	}

}
