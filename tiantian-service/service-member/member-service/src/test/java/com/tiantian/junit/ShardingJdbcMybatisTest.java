package com.tiantian.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiantian.member.apis.UserService;
import com.tiantian.member.bo.UserBo;
import com.tiantian.member.vo.UserVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext*.xml"})
public class ShardingJdbcMybatisTest {

	@Autowired
	public UserService userService;
	@Test
	public void testUserInsert() {
		UserBo u = new UserBo(); 
		u.setEmail("572361524@qq.com");
		u.setId(50);
		u.setPassword("2585201312");
		u.setPhone("15969419204");
		u.setUsername("lzw220");
		//userService.register(u);
	}

	@Test
	public void testStudentInsert() {}

	@Test
	public void testFindAll(){
		//UserVo u=userService.findByUserName("lzw220");
		//System.out.println("============================="+u);
	}

	@Test
	public void testSQLIN(){}

	@Test
	public void testSQLWhere(){}

}
