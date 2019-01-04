package com.example.miaoshademo.service.model;

import java.math.BigDecimal;

public class OrderModel {

    private String id;
    //购买的yonghuid
    private Integer userId;
    //购买的商品id
    private Integer itemId;
    //购买数量
    private Integer amount;
    //购买金额
    private BigDecimal orderPrice;
    //购买商品的单价
    private BigDecimal itemPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderAmount() {
        return orderPrice;
    }

    public void setOrderAmount(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
