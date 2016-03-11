package com.maiquan.aladdin_shopcar;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start 
{
    public static void main( String[] args ) throws Exception{
    	
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		context.start();
		System.out.println("购物车微服务启动");
		while(true){
			Thread.sleep(1000);
		}
    }
}
