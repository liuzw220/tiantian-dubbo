package com.tiantian.store.order.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.tiantian.core.beans.PageResult;
import com.tiantian.store.order.bean.Where;
import com.tiantian.store.order.mapper.OrderMapper;
import com.tiantian.store.order.pojo.Order;


@Service
public class OrderDao extends BaseDao<Order>{
	
	@Autowired
	private OrderMapper orderMapper;

	 
	public void createOrder(Order order) {
		super.save(order);
	}

	 
	public Order queryOrderById(String orderId) {
		return super.findById(orderId);
	}

	 
	public PageResult<Order> queryOrderByUserNameAndPage(String buyerNick, Integer page, Integer count) {
		PageBounds bounds = new PageBounds();
		bounds.setContainsTotalCount(true);
		bounds.setLimit(count);
		bounds.setPage(page);
		bounds.setOrders(com.github.miemiedev.mybatis.paginator.domain.Order.formString("create_time.desc"));
		PageList<Order> pageDate = this.orderMapper.queryListByWhere(bounds, Where.build("buyer_nick", buyerNick));
		return new PageResult<Order>(Long.valueOf(pageDate.getPaginator().getTotalCount()), null);
	}

	 
	public void changeOrderStatus(Order order) {
		try {
			order.setUpdateTime(new Date());
			super.update(order);
		} catch (Exception e) {
			e.printStackTrace();
			 //new ResultMsg("500", "更新订单出错!");
		}
		//return new ResultMsg("200", "更新成功!");
	}

}
