package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import com.CabbageAndGarlic.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final RoutingRepository routingRepository;

    public List<Routing> findAll() {
        return routingRepository.findAll();
    }

    public List<RoutingListViewResponse> findRoutingItemsByRoutingNumber(Long routingNumber) {
        List<Routing> routings = routingRepository.findByNumber(routingNumber);
        if (routings.isEmpty()) {
            throw new IllegalStateException("라우팅을 찾을 수 없습니다: " + routingNumber);
        }
        return routings.stream()
                .map(RoutingListViewResponse::new)
                .collect(Collectors.toList());
    }

}
