package gzq.upc.service.impl;

import gzq.upc.VO.ProductInfoVO;
import gzq.upc.dataobject.ProductInfo;
import gzq.upc.dto.CartDTO;
import gzq.upc.enums.ProductStatusEnum;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.repository.ProductInfoRepository;
import gzq.upc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Integer supermarket,Pageable pageable) {
        return repository.findBySupermarket(supermarket,pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    public ProductServiceImpl() {
        super();
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }



    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfoOptional = repository.findById(productId);
        if(productInfoOptional == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfoOptional.get().getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        ProductInfo productInfo = productInfoOptional.get();
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return  repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfoOptional = repository.findById(productId);
        if(productInfoOptional == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfoOptional.get().getProductStatus() == ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        ProductInfo productInfo = productInfoOptional.get();
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        return  repository.save(productInfo);
    }
}
