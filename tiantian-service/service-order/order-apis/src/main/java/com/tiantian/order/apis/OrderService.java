package com.tiantian.order.apis;


import com.tiantian.core.beans.PageResult;
import com.tiantian.order.bo.OrderBo;
import com.tiantian.order.vo.OrderVo;

/**
 * 订单DAO接口
 */
public interface OrderService {

    /**
     * 创建订单
     * 
     * @param order
     */
    public String createOrder(OrderBo order);

    /**
     * 根据订单ID查询订单
     * 
     * @param orderId
     * @return
     */
    public OrderVo queryOrderById(String orderId);

    /**
     * 根据用户名分页查询订单信息
     * 
     * @param buyerNick 买家昵称，用户名
     * @param start 分页起始取数位置
     * @param count 查询数据条数
     * @return 分页结果集
     */
    public PageResult<OrderVo> queryOrderByUserNameAndPage(String buyerNick, Integer page, Integer count);

    /**
     * 更改订单状态，由service层控制修改逻辑
     * 
     * @param order
     * @return
     */
    public void changeOrderStatus(OrderBo order);

}
