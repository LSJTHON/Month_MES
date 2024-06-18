package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
