package com.shopping.entity;

import javax.persistence.*;

/**
 * Created by 14437 on 2017/3/3.
 */
@Entity
@Table(name="shopping_car")
@IdClass(value=ShoppingCarPriKey.class)
public class ShoppingCar {
    private int userId;
    private int productId;
    private int productPrice;
    private int counts;
    //@Column用来标识实体类中属性与数据表中字段的对应关系
    @Id
    @Column(name="user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name="product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name="product_price")
    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Column(name="counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
