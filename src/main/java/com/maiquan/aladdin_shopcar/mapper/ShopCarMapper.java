package com.maiquan.aladdin_shopcar.mapper;

import com.maiquan.aladdin_shopcar.domain.ShopCar;

public interface ShopCarMapper {
    int deleteByPrimaryKey(String shopCarID);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(String shopCarID);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
    
    ShopCar selectShopCarByUserID(String userID);
}