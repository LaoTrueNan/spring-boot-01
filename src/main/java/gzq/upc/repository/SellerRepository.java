package gzq.upc.repository;

import gzq.upc.dataobject.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Integer>{
    Seller findByName(String name);
}
