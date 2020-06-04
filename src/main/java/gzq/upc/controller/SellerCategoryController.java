package gzq.upc.controller;

import gzq.upc.dataobject.ProductCategory;
import gzq.upc.dataobject.SellerInfo;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.form.CategoryForm;
import gzq.upc.service.CategoryService;
import gzq.upc.service.SellerInfoService;
import gzq.upc.utils.gzqCookie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map, HttpServletRequest httpServletRequest) {
        String username = gzqCookie.getMyCookie(httpServletRequest,"username");
        SellerInfo sellerInfo = sellerInfoService.findByUsername(username);
        List<ProductCategory> productCategoryList = categoryService.findBySupermarket(sellerInfo.getId());
        map.put("categoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }

        return new ModelAndView("category/index",map);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             HttpServletRequest httpServletRequest) {
        String username = gzqCookie.getMyCookie(httpServletRequest,"username");
        SellerInfo sellerInfo = sellerInfoService.findByUsername(username);
        List<ProductCategory> productCategoryList = categoryService.findBySupermarket(sellerInfo.getId());
        List<ProductCategory> productCategoryList1 = categoryService.findAll();
        List<String> categorynames = productCategoryList.stream().map(ProductCategory::getCategoryName).collect(Collectors.toList());
        List<Integer> categorytypes = productCategoryList1.stream().map(ProductCategory::getCategoryType).collect(Collectors.toList());
        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
                if(!form.getCategoryName().equals(productCategory.getCategoryName()) && categorynames.indexOf(form.getCategoryName())!=-1){
                    throw new SellException(ResultEnum.ALREADY_EXIST);
                }else if(form.getCategoryType()!=productCategory.getCategoryType() && categorytypes.indexOf(form.getCategoryType())!=-1){
                    throw new SellException(ResultEnum.TYPE_EXIST);
                }
            }else{
                form.setSupermarket(sellerInfo.getId());
                BeanUtils.copyProperties(form,productCategory);
                if(categorynames.indexOf(productCategory.getCategoryName())!=-1){
                    throw new SellException(ResultEnum.ALREADY_EXIST);
                }else if(categorytypes.indexOf(productCategory.getCategoryType())!=-1){
                    throw new SellException(ResultEnum.TYPE_EXIST);
                }
            }
            categoryService.save(productCategory);
        } catch (SellException e) {
            return e.getMessage();
        }

        return "/seller/category/list";
    }

    @GetMapping("/del")
    @ResponseBody
    public String del(@RequestParam("categoryId") Integer categoryId){
        ProductCategory productCategory = categoryService.findOne(categoryId);
        try{
            categoryService.delete(productCategory);
        }catch(SellException e){
            return e.getMessage();
        }
        return "/seller/category/list";
    }
}
