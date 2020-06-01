package com.shopping.service;

import com.shopping.entity.Product;

import java.util.List;

public interface Face {

    public List<String> faceRecomment(String face);

    public List<Product> getProductsByFace(String searchKeyWord);

}
