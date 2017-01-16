package com.tiantian.order.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiantian.core.beans.PageResult;
import com.tiantian.order.apis.OrderService;
import com.tiantian.order.bean.ResultMsg;
import com.tiantian.order.bean.TiantianResult;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.order.util.ValidateUtil;
import com.tiantian.order.vo.OrderVo;

@Service
public class OrderServiceBusiness {

	@Autowired
	private OrderService  orderService;

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public TiantianResult createOrder(String json) {
		OrderBo order = null;
		try {
			order = objectMapper.readValue(json, OrderBo.class);
			// 校验Order对象
			ValidateUtil.validate(order);
		} catch (Exception e) {
			return TiantianResult.build(400, "请求参数有误!");
		}
		try {
			String orderId = orderService.createOrder(order);
			return TiantianResult.ok(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TiantianResult.build(400, "保存订单失败!");
	}

	public OrderVo queryOrderById(String orderId) {
		OrderVo order = orderService.queryOrderById(orderId);
		return order;
	}

	public PageResult<OrderVo> queryOrderByUserNameAndPage(String buyerNick,
			Integer page, Integer count) {
		OrderBo bo=new OrderBo();
		bo.setBuyerNick(buyerNick);
		bo.setPageIndex(page);
		bo.setPageSize(count);
		return orderService.queryOrderByUserNameAndPage(bo);
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
