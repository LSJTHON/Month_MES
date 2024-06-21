package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import com.CabbageAndGarlic.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final RoutingRepository routingRepository;

    public List<Routing> findAll() {
        return routingRepository.findAll();
    }


}
