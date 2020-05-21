package gzq.upc.repository;

import gzq.upc.dataobject.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks,Integer> {
    Tasks findByOrderId(String orderId);
    List<Tasks> deleteByDeliveryId(String deliveryId);
}
