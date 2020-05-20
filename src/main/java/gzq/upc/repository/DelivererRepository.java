package gzq.upc.repository;

import gzq.upc.dataobject.Deliverer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelivererRepository extends JpaRepository<Deliverer,String>{
    Page<Deliverer> findByStatus(Integer status,Pageable pageable);

    Deliverer findByDeliveryId(String deliveryId);
}
