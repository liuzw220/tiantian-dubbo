package com.tiantian.member.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.member.apis.UserService;
import com.tiantian.member.bo.UserBo;
import com.tiantian.member.dao.UserDao;
import com.tiantian.member.pojo.User;
import com.tiantian.member.vo.UserVo;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired(required=false)
	private Mapper dozerMapper;
	@Override
	public Boolean checkUser(String param, Integer type) {
		return userDao.checkUser(param, type);
	}
	@Override
	public Boolean register(UserBo userBo) {
		User user= dozerMapper.map(userBo, User.class);
		return userDao.register(user);
	}
	@Override
	public UserVo findByUserName(String username) {
		User user=userDao.findByUserName(username);
		return dozerMapper.map(user, UserVo.class);
	}
	@Override
	public String saveRedis(UserBo userBo) {
		User user= dozerMapper.map(userBo, User.class);
		return userDao.saveRedis(user);
	}
	@Override
	public UserVo queryUserByTicket(String ticket) {
		User user=userDao.queryUserByTicket(ticket);
		return dozerMapper.map(user, UserVo.class);
	}

}
