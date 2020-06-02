package gzq.upc.service.impl;

import gzq.upc.dataobject.ProductCategory;
import gzq.upc.dataobject.ProductInfo;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.repository.ProductCategoryRepository;
import gzq.upc.repository.ProductInfoRepository;
import gzq.upc.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{


    public CategoryServiceImpl() {
        super();
    }

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findBySupermarket(Integer supermarket) {
        return repository.findBySupermarket(supermarket);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    @Transactional
    public ProductCategory delete(ProductCategory productCategory) {
        List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(productCategory.getCategoryType());
        if(productInfoList.size()!=0){
            throw new SellException(ResultEnum.CATEGORY_DELETE_FAIL);
        }else{
            repository.delete(productCategory);
        }
        return productCategory;
    }

}
