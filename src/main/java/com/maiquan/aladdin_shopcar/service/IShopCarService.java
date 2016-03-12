package com.maiquan.aladdin_shopcar.service;

import java.util.List;
import java.util.Map;

public interface IShopCarService {

	/**
	 * 查找购物车
	 * @param userID
	 * @param requestID
	 * @return
	 */
	List<Map<String,Object>> viewShopCar(String userID, String requestID);
	
	/**
	 * 添加商品到购物车
	 * @param userID
	 * @param productID
	 * @param skuID 
	 * @param buyNum
	 * @param requestID
	 * @return
	 */
	int addToShopCar(Integer userID, Integer productID, Integer skuID, Integer buyNum, String requestID);
	
	/**
	 * 从购物车移除商品
	 * @param userID
	 * @param skuID
	 * @param requestID
	 * @return
	 */
	int removeShopCarProduct(Integer userID, Integer[] skuIDs, String requestID);
	
	/**
	 * 清空购物车
	 * @param userID
	 * @param requestID
	 * @return
	 */
	int emptyShopCar(Integer userID, String requestID);
}
