package com.shopping.entity;

import javax.persistence.*;

/**
 * Created by 14437 on 2017/3/7.
 */
@Entity
@Table(name="evaluation")
@IdClass(value=ShoppingRecordPriKey.class)
public class Evaluation {
    private int userId;
    private int productId;
    private String time;
    private String content;

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

    @Id
    @Column(name="time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
