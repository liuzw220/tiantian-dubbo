package com.tiantian.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	public static void main(String[] args) throws Exception {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
			context.start();
			Thread.sleep(2000);
			System.out.println("dubbo-server服务正在监听，按任意键退出");
			System.in.read();
	}	
}
