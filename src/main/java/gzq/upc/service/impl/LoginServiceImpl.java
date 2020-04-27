package gzq.upc.service.impl;

import gzq.upc.dataobject.SellerInfo;
import gzq.upc.repository.SellerInfoRepository;
import gzq.upc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public String check(String username, String password) {
        List<SellerInfo> sellerInfoList = sellerInfoRepository.findAll();
        SellerInfo sellerInfo = sellerInfoRepository.findByUsername(username);
        if(sellerInfo == null){
            return "No Such User";
        } else{
            if(sellerInfo.getPassword().equals(password)){
                return "success";
            }else{
                return "Wrong Password";
            }
        }
    }
}
