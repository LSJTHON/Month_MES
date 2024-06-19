package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product saveProduct(AddProductRequest request) {
        return productRepository.save(request.toEntity());
    }

    public void delete(String productCode){
        productRepository.deleteById(productCode);
    }

}
