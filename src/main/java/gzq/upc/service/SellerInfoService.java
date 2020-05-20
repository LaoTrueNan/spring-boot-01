package gzq.upc.service;

import gzq.upc.dataobject.SellerInfo;

public interface SellerInfoService {
    SellerInfo findByUsername(String username);

    SellerInfo save(SellerInfo sellerInfo);
}
