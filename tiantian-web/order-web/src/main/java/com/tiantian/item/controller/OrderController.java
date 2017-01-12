package com.tiantian.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.bean.ResultMsg;
import com.tiantian.item.bean.TaotaoResult;
import com.tiantian.item.business.OrderServiceBusiness;
import com.tiantian.order.vo.OrderVo;


@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderServiceBusiness orderBusiness;
	
	/**
	 * 创建订单
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public TaotaoResult createOrder(@RequestBody String json) {
		return orderBusiness.createOrder(json);
	}
	
	
	/**
	 * 根据订单ID查询订单
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query/{orderId}" ,method = RequestMethod.GET)
	public OrderVo queryOrderById(@PathVariable("orderId") String orderId) {
		return orderBusiness.queryOrderById(orderId);
	}

	/**
	 * 根据用户名分页查询订单
	 * @param buyerNick
	 * @param page
	 * @param count
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query/{buyerNick}/{page}/{count}")
	public PageResult<OrderVo> queryOrderByUserNameAndPage(@PathVariable("buyerNick") String buyerNick,@PathVariable("page") Integer page,@PathVariable("count") Integer count) {
		return orderBusiness.queryOrderByUserNameAndPage(buyerNick, page, count);
	}

	
	/**
	 * 修改订单状态
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/changeOrderStatus",method = RequestMethod.POST)
	public ResultMsg changeOrderStatus(@RequestBody String json) {
		return orderBusiness.changeOrderStatus(json);
	}
}
