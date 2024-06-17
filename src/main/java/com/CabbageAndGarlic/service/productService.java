package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.repository.productRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class productService {
    private final productRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
