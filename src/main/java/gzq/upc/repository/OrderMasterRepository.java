package gzq.upc.repository;
import gzq.upc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{
    Page<OrderMaster> findBySupermarket(Integer supermarket, Pageable pageable);
}
