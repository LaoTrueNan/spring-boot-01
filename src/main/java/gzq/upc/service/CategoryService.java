package gzq.upc.service;

import gzq.upc.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findBySupermarket(Integer supermarket);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory delete(ProductCategory productCategory);


}
