package gzq.upc.controller;

import gzq.upc.dataobject.ProductCategory;
import gzq.upc.dataobject.ProductInfo;
import gzq.upc.dataobject.SellerInfo;
import gzq.upc.exception.SellException;
import gzq.upc.form.ProductForm;
import gzq.upc.service.CategoryService;
import gzq.upc.service.ProductService;
import gzq.upc.service.SellerInfoService;
import gzq.upc.utils.KeyUtil;
import gzq.upc.utils.gzqCookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String,Object> map,
                             HttpServletRequest httpServletRequest){
        PageRequest request = PageRequest.of(page-1,size);
        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        SellerInfo sellerInfo = sellerInfoService.findByUsername(currentUser);
        Integer supermarket = sellerInfo.getId();
        Page<ProductInfo> productInfoPage = productService.findAll(supermarket,request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    @GetMapping("/onsale")
    @ResponseBody
    public String onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch(SellException e){
            return e.getMessage();
        }
        return "/seller/product/list";
    }

    @GetMapping("/offsale")
    @ResponseBody
    public String offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch(SellException e){
            return e.getMessage();
        }
        return "/seller/product/list";
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                                Map<String,Object> map,HttpServletRequest httpServletRequest){
        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        SellerInfo sellerInfo = sellerInfoService.findByUsername(currentUser);
        Integer supermarket = sellerInfo.getId();
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList = categoryService.findBySupermarket(supermarket);
        map.put("categoryList",productCategoryList);

        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@Valid ProductForm form,HttpServletRequest httpServletRequest){
        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        SellerInfo sellerInfo = sellerInfoService.findByUsername(currentUser);
        Integer supermarket = sellerInfo.getId();
        ProductInfo productInfo = new ProductInfo();
        try{
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
                form.setProductStatus(1);
            }
            BeanUtils.copyProperties(form,productInfo);
            productInfo.setSupermarket(supermarket);
            productService.save(productInfo);
        }catch(SellException e){
            return e.getMessage();
        }

        return "/seller/product/list";
    }
}
