package gzq.upc.service;

import gzq.upc.dataobject.Deliverer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    Page<Deliverer> findList(Integer status,Pageable pageable);

    Deliverer findOne(String idNum);

    Deliverer findByDeliveryId(String deliveryId);

    Deliverer update(String idNum,Integer sellId);

    Deliverer fire(String idNum);
}
