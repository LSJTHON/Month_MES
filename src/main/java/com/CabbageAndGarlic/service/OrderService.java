package com.CabbageAndGarlic.service;


import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

//    public Order save(OrderDto request){
//        return orderRepository.save(request.)
//    }


}
