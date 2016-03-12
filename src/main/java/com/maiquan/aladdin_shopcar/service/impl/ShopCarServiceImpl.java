package com.maiquan.aladdin_shopcar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maiquan.aladdin_product.domain.Product;
import com.maiquan.aladdin_product.domain.ProductSku;
import com.maiquan.aladdin_product.service.IProductService;
import com.maiquan.aladdin_product.service.IProductSkuService;
import com.maiquan.aladdin_shopcar.domain.ShopCar;
import com.maiquan.aladdin_shopcar.domain.ShopCarProduct;
import com.maiquan.aladdin_shopcar.mapper.ShopCarMapper;
import com.maiquan.aladdin_shopcar.mapper.ShopCarProductMapper;
import com.maiquan.aladdin_shopcar.service.IShopCarService;
import com.maiquan.aladdin_shopcar.util.LogUtil;
import com.maiquan.aladdin_supplier.domain.Supplier;
import com.maiquan.aladdin_supplier.service.ISupplierService;

@Service
public class ShopCarServiceImpl implements IShopCarService{

	@Autowired
	private ShopCarMapper shopCarMapper;
	
	@Autowired
	private ShopCarProductMapper shopCarProductMapper;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IProductSkuService productSkuService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Override
	public List<Map<String,Object>> viewShopCar(String userID, String requestID) {
		
		LogUtil.logInput("购物车微服务", "viewShopCar", requestID, userID);
		
		List<Map<String,Object>> shopCarProductsMap = new ArrayList<Map<String,Object>>();
		
		ShopCar shopCar = shopCarMapper.selectShopCarByUserID(userID);
		
		if(shopCar==null){
			shopCar =  new ShopCar();
			shopCar.setShopCarID(UUID.randomUUID().toString().substring(0, 20));
			shopCar.setUserID(userID);
			shopCarMapper.insert(shopCar);
		}
		
		ShopCarProduct shopCarProduct = new ShopCarProduct();
		
		shopCarProduct.setShopCarID(shopCar.getShopCarID());
		
		List<ShopCarProduct> shopCarProducts = shopCarProductMapper.selectByCondition(shopCarProduct);
		
		for(int i=0;i<shopCarProducts.size();i++){
			Integer skuID = shopCarProducts.get(i).getSkuID();
			System.out.println("skuID----"+skuID);
			ProductSku sku = productSkuService.getSkuByID(skuID, UUID.randomUUID().toString());
			System.out.println("sku-----"+sku);
			Product product = productService.queryProduct(sku.getProductID(), UUID.randomUUID().toString());
			Supplier supplier = supplierService.getSupplier(product.getSupplyID(), UUID.randomUUID().toString());
			
			List<String> skuStrs = productSkuService.getSkuStr(skuID, UUID.randomUUID().toString());
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			//1 存储供应商
			map.put("supName", supplier.getName());
			//2 存储skuID
			map.put("skuID", sku.getID());
			//3 存储sku对应的图片
			map.put("imgPath",sku.getSkuImg());
			//4 存储sku对应的商品描述
			map.put("sellDesc", product.getSellDesc());
			//5 存储sku对应的sku属性
			map.put("skuStrs",skuStrs);
			//6 存储sku对应的价格
			map.put("skuPrice", sku.getSkuPrice());
			//7 存储sku对应的购买数量
			map.put("skuQuality", shopCarProducts.get(i).getQuality());
			
			
			shopCarProductsMap.add(map);
			
		}
		
		LogUtil.logOutput("购物车微服务", "viewShopCar", requestID, shopCarProductsMap);
		
		return shopCarProductsMap;
	}

	@Override
	public int addToShopCar(Integer userID, Integer productID, Integer skuID, Integer buyNum,
			String requestID) {
		
		LogUtil.logInput("购物车微服务", "addToShopCar", requestID, userID, productID, skuID, buyNum);
		
		System.out.println("-------------------"+userID);
		ShopCar shopCar = shopCarMapper.selectShopCarByUserID(userID+"");
		
		if(shopCar==null){
			System.out.println("shopCar为空");
			shopCar =  new ShopCar();
			shopCar.setShopCarID(UUID.randomUUID().toString().substring(0, 20));
			shopCar.setUserID(userID+"");
			shopCarMapper.insert(shopCar);
		}
		
		//先从数据库查找 如果没有 就创建
		ShopCarProduct shopCarProduct = new ShopCarProduct();
		shopCarProduct.setShopCarID(shopCar.getShopCarID());
		shopCarProduct.setProductID(productID);
		shopCarProduct.setSkuID(skuID);
		
		List<ShopCarProduct> shopCarProductList = shopCarProductMapper.selectByCondition(shopCarProduct);
		
		if(shopCarProductList.size()==0){
			System.out.println("当前购物车没有 该商品");
			shopCarProduct.setID((int)(Math.random()*1000000000)+"");
			shopCarProduct.setQuality(buyNum);
			shopCarProductMapper.insert(shopCarProduct);
			
		}else{
			System.out.println("当前购物车已有 该商品");
			System.out.println("欲购买数量:------"+buyNum);
			System.out.println("购物车商品的ID为------"+shopCarProductList.get(0).getID());
			shopCarProductList.get(0).setQuality(buyNum);
			shopCarProductMapper.updateByPrimaryKey(shopCarProductList.get(0));
			
		}
		
		LogUtil.logOutput("购物车微服务", "addToShopCar", requestID, "无");
		
		return 0;
	}

	@Override
	public int removeShopCarProduct(Integer userID, Integer[] skuIDs, String requestID) {
	
		LogUtil.logInput("购物车微服务", "removeShopCarProduct", requestID, userID);
			
		//先找出该用户对应的购物车
		ShopCar shopCar = shopCarMapper.selectShopCarByUserID(userID+"");
		
		if(shopCar == null){
			shopCar =  new ShopCar();
			shopCar.setShopCarID(UUID.randomUUID().toString().substring(0, 20));
			shopCar.setUserID(userID+"");
			shopCarMapper.insert(shopCar);
		}
		
		shopCarProductMapper.deleteByShopCarIDAndSkuID(shopCar.getShopCarID(),skuIDs);
		
		LogUtil.logOutput("购物车微服务", "removeShopCarProduct", requestID, "无");
		
		return 0;
	}

	@Override
	public int emptyShopCar(Integer userID, String requestID) {
		
		LogUtil.logInput("购物车微服务", "emptyShopCar", requestID, userID);
		
		ShopCar shopCar = shopCarMapper.selectShopCarByUserID(userID+"");
		System.out.println(shopCar.getShopCarID());
		
		shopCarProductMapper.deleteByShopCarID(shopCar.getShopCarID());
		
		LogUtil.logOutput("购物车微服务", "emptyShopCar", requestID, "无");
		
		return 0;
	}

}
