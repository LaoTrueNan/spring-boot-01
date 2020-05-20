package gzq.upc.service.impl;

import gzq.upc.dataobject.SellerInfo;
import gzq.upc.repository.SellerInfoRepository;
import gzq.upc.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService{

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo){
        return repository.save(sellerInfo);
    }
}
