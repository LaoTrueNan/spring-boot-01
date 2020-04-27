package gzq.upc.controller;

import gzq.upc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("/add")
    public String addProduct(@RequestParam("productId") String productId){
        return "success!";
    }
//
}
