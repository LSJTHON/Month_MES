package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import com.CabbageAndGarlic.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final RoutingRepository routingRepository;

    public List<Routing> findAll() {
        return routingRepository.findAll();
    }

    public List<RoutingListViewResponse> findRoutingItemsByRoutingNumber(int routingNumber) {
        List<Routing> routings = routingRepository.findByNumber(routingNumber);

        List<RoutingListViewResponse> resultList = new ArrayList<>();
        for (Routing routing : routings) {
            resultList.add(new RoutingListViewResponse(routing));
        }
        return resultList;
    }


    public void deleteRouting(int number){
        routingRepository.deleteById(number);
    }


}
