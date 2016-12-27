
package com.liuzw.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tiantian.manager.business.ContentCategoryBusiness;

public class Consumer {
	
    public static void main(String[] args) throws Exception {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        context.start();
        ContentCategoryBusiness  contentCategoryBusiness= context.getBean(ContentCategoryBusiness.class);
        contentCategoryBusiness.deleteById(1L);
        // 获取远程服务代理
       System.out.println("远程方法成功执行"); // 显示调用结果
    }

}
