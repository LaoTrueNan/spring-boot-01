package gzq.upc.service;

import gzq.upc.dataobject.SellerInfo;

import java.util.List;

public interface SellerInfoService {
    SellerInfo findByUsername(String username);

    SellerInfo save(SellerInfo sellerInfo);

    List<SellerInfo> findAll();
}
