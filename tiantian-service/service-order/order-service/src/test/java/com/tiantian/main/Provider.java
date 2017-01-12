
package com.tiantian.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("resource")
public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		context.start();
		Thread.sleep(2000);
		//UserServiceImp user=context.getBean(UserServiceImp.class);
		System.out.println("dubbo-server服务正在监听，按任意键退出");
		System.in.read();
	}
}
