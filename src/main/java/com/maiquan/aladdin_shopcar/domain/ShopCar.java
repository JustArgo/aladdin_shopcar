package com.maiquan.aladdin_shopcar.domain;

import java.io.Serializable;

public class ShopCar implements Serializable{
	
	private static final long serialVersionUID = -7595727150643024242L;

	private String shopCarID;

    private String userID;

    public String getShopCarID() {
        return shopCarID;
    }

    public void setShopCarID(String shopCarID) {
        this.shopCarID = shopCarID == null ? null : shopCarID.trim();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }
}