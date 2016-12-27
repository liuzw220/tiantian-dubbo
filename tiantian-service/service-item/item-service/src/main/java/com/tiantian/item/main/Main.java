
package com.tiantian.item.main;


public class Main {
	public static void main(String[] args) throws Exception {
		com.alibaba.dubbo.container.Main.main(args);
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		context.start();
		Thread.sleep(2000);
		//UserServiceImp user=context.getBean(UserServiceImp.class);
		System.out.println("dubbo-server服务正在监听，按任意键退出");
		System.in.read();*/
	}	
}
