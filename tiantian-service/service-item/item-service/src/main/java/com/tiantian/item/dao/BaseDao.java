package com.tiantian.item.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.tiantian.core.mapper.plugin.TianTianMapper;
import com.tiantian.item.pojo.BasePojo;

/**
 * 通用service，用于简单的处理业务
 * 通过泛型接口注入
 * @author schoolBoy
 *
 */
public abstract class BaseDao<T extends BasePojo> {

	@Autowired
	private TianTianMapper<T> mapper;
	//注入rabbitMq模板
	@Autowired(required=false)
	private RabbitTemplate rabbitTemplate;

	/**
	 * 通过主键获取
	 * @param id
	 * @return
	 */
	public T findById(Long id){
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 更具条件查找一个对象，如果找到多个返回第一个，如果没有找到则返回null
	 * @param t 条件对象
	 * @return 结果对象
	 */
	public T findSingle(T t){
		List<T> list = mapper.select(t);
		if(!list.isEmpty()&&list.size()>0)
			return list.get(0);
		else return null;
	}
	/**
	 * 条件查询,通过对象构造条件 <br/>不分页
	 * @param t 条件对象
	 * @return 结果对象集合 <br/>不包含分页信息
	 */
	public List<T> queryList(T t){
		return mapper.select(t);
	}


	/**
	 * 条件查询,通过对象构造条件 <br/>分页
	 * @param t 条件对象
	 * @return 结果对象集合 <br/>包含分页信息
	 */
	public PageInfo<T> queryListPage(T t) {
		List<T> list = this.queryList(t);
		return new PageInfo<T>(list);
	}

	/**
	 * 通过条件，查询数量
	 * @param t 条件对象
	 * @return 结果对象集合满足条件的总记录条数
	 */
	public Long queryListCount(T t){
		return this.mapper.selectCount(t);
	}

	/**
	 * 新增数据
	 * @param t
	 * @return 受影响数据行数
	 */
	public Integer save(T t){
		t.setCreated(new Date());
		t.setUpdated(t.getCreated());
		return this.mapper.insert(t);
	}

	/**
	 * 新增数据，不为null作为参数插入
	 * @param t
	 * @return 受影响数据行数
	 */
	public Integer saveSelective(T t){
		t.setCreated(new Date());
		t.setUpdated(t.getCreated());
		return this.mapper.insertSelective(t);
	}

	/**
	 * 更新数据
	 * @param t 需要跟新的对象
	 * @return 受影响数据行数
	 */
	public Integer update(T t){
		t.setCreated(null);
		t.setUpdated(new Date());
		return this.mapper.updateByPrimaryKey(t);
	}

	/**
	 * 更新数据，不为null作为参数插入
	 * @param t 需要跟新的对象
	 * @return 受影响数据行数
	 */
	public Integer updateSelective(T t){
		t.setCreated(null);
		t.setUpdated(new Date());
		return this.mapper.updateByPrimaryKeySelective(t);
	}
	/**
	 * 通过主键删除
	 * @param id
	 * @return
	 */
	public Integer deleteById(Serializable id){
		return this.mapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除
	 * @param ids 要删除的id集合
	 * @return
	 */
	public Integer deleteByIds(Long[] ids){
		return mapper.deleteByIDS(ids);
	}

	/**
	 * 向交换机发送一条消息(发送到该交换机绑定的队列中)
	 * @param key 消息的键
	 * @param value 消息的内容
	 */
	protected void sendMsg(String key,Object value){
		System.out.println("消息发送:key="+key+"value="+value);
		rabbitTemplate.convertAndSend(key, value);
	}
}
