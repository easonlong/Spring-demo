package com.eason.spring.jdbc.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eason.spring.jdbc.entity.Points;
import com.eason.spring.jdbc.service.PointsService;


public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-applicationContext.xml");
    	final PointsService pointsService=(PointsService) ctx.getBean("pointsService");
    	final PointsService pointsService2=(PointsService) ctx.getBean("pointsService");
    	final Points points=new Points();
    	points.setUid("23");
    	points.setPoints(123);
    	
    	final Points points2=new Points();
    	points2.setUid("24");
    	points2.setPoints(123);
    	
    	new Thread(new Runnable() {
			
			public void run() {
				Points points=pointsService.query("21");
				if(points==null){
					System.out.println("null");
				}else{
					System.out.println(points.getUid());
				}
				
			}
		}).start();
    	new Thread(new Runnable() {
			
			public void run() {
				Points points=pointsService2.query("22");
				if(points==null){
					System.out.println("null");
				}else{
					System.out.println(points.getUid());
				}
				
			}
		}).start();
    }
}
