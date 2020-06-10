package com.shopping.controller;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import com.shopping.service.FaceImpl;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCarService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  该类用于人脸识别测试controller
 */
@Controller
public class FaceControl {

    @Autowired
    private FaceImpl faceImpl;

    @Resource
    private ShoppingCarService shoppingCarService;
    @Resource
    private ShoppingRecordService shoppingRecordService;
    @Resource
    private ProductService productService;



    @RequestMapping(value = "/face",method = RequestMethod.POST)
    @ResponseBody
    public List<String> face(String image, HttpSession httpSession){
        System.out.println(image);
//        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
//        String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
//        String pictore = image;
//        String path = "";
//        String s = faceImpl.GetImageStr(webappRoot+"static/userImg/"+path);

    //   根据图片人物个数返回表情个数
        List<String> strings = faceImpl.faceIdentify(image);
     //拿到表情
        String s = strings.get(0);
        List<String> faceRes = faceImpl.faceRecomment(s);
        try {
            s = URLEncoder.encode(s,"utf-8");
            s = new String(s.getBytes("GBK"),"UTF-8");
            httpSession.setAttribute("faceRes", faceRes);
        }catch (Exception e){

        }

        return faceRes;
    }

    @RequestMapping(value = "/getAllProductFaceRecomand")
    @ResponseBody
    public Map<String,Object> getAllProductFaceRecomand(String faceCode){
        HttpSession httpSession = null;

        System.out.println("用户的表情编码："+faceCode);
        List<Product> productList = new ArrayList<>();
        List<Product> productTem = new ArrayList<>();
        List<Product> productRcommand = new ArrayList<>();
        productList = productService.getAllProduct();

        //表情自然为7
        if (faceCode != null) {

            /**
             * 获取对应表情相关的数据
             */

            productList = productService.getProductsByFace(faceCode);

            for (Product product : productList) {
                product.setType(8);
            }
        }

        /**
         * 对信息进行封装
         */
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }


}
