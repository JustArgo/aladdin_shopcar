package com.maiquan.aladdin_shopcar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maiquan.aladdin_shopcar.domain.ShopCarProduct;

public interface ShopCarProductMapper {
    int deleteByPrimaryKey(String ID);

    int deleteByShopCarIDAndSkuID(@Param("shopCarID")String shopCarID, @Param("skuID")Integer skuID);
    
    int insert(ShopCarProduct record);

    int insertSelective(ShopCarProduct record);

    ShopCarProduct selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(ShopCarProduct record);

    int updateByPrimaryKey(ShopCarProduct record);
    
    List<ShopCarProduct> selectByCondition(ShopCarProduct shopCarProduct);
    
    void deleteByShopCarID(String shopCarID);

}