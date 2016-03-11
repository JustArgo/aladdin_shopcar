package com.maiquan.aladdin;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maiquan.aladdin_shopcar.service.IShopCarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestShopCarService{ 
    
	@Autowired
	private IShopCarService shopCarService;
	
	/**
	 * 测试查找出某个用户的购物车
	 */
	@Test
	public void testQueryShopCar(){
		
		List<Map<String,Object>> shopCar = shopCarService.viewShopCar(1+"", "");
		
		for(int i=0;i<shopCar.size();i++){
			Map<String,Object> map = shopCar.get(i);
			System.out.println(map.get("imgPath"));
			System.out.println(map.get("sellDesc"));
			System.out.println(map.get("skuPrice"));
			System.out.println(map.get("skuStrs"));
		}
		
	}
	
	/**
	 * 测试添加商品到购物车
	 */
	@Test
	public void testAddToShopCar(){
		
		shopCarService.addToShopCar(154, 2367, 3, 5, "");
		
	}
	
	/**
	 * 测试从购物车 移除掉某件商品
	 */
	@Test
	public void testRemoveShopCarProduct(){
		
		shopCarService.removeShopCarProduct(1, 233, "");
		
	}
	
	/**
	 * 测试清空购物车
	 */
	@Test
	public void testEmptyShopCar(){
		
		shopCarService.emptyShopCar(4, "");
		
	}
	
}
