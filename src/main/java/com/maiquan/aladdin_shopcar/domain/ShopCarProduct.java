package com.maiquan.aladdin_shopcar.domain;

import java.io.Serializable;

public class ShopCarProduct implements Serializable{

	private static final long serialVersionUID = -8554110481663887271L;

	private String ID;

    private String shopCarID;

    private Integer productID;
    
    private Integer skuID;

	private Integer quality;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID == null ? null : ID.trim();
    }

    public String getShopCarID() {
        return shopCarID;
    }

    public void setShopCarID(String shopCarID) {
        this.shopCarID = shopCarID == null ? null : shopCarID.trim();
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getSkuID() {
		return skuID;
	}

	public void setSkuID(Integer skuID) {
		this.skuID = skuID;
	}
    
    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

	@Override
	public String toString() {
		return "ShopCarProduct [ID=" + ID + ", shopCarID=" + shopCarID
				+ ", productID=" + productID + ", skuID=" + skuID
				+ ", quality=" + quality + "]";
	}
    
    
}