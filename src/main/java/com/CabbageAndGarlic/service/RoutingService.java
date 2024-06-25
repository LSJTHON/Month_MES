package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.dto.AddRoutingRequest;
import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import com.CabbageAndGarlic.repository.RoutingRepository;
import jakarta.transaction.Transactional;
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

//    public Routing saveRouting(AddRoutingRequest request) {
//        return routingRepository.save(request.toEntity());
//    }

//    @Transactional
//    public void saveRouting(AddRoutingRequest request) {
//        Routing routing = request.toEntity();
//        routingRepository.save(routing);
//
//        for (AddRoutingRequest addRoutingRequest : request.getRoutingNumber()) {
//            Routing routing1 = new Routing();
//            routing1.setAllCycleTime(addRoutingRequest.getAllCycleTime());
//            routing1.setRoutingProductName(addRoutingRequest.getRoutingProductName());
//            routing1.setRoutingNumber(addRoutingRequest.getRoutingNumber());
//
//            routingRepository.save(routing1);
//        }
//    }


    public void deleteRouting(int number) {
        routingRepository.deleteById(number);
    }


}
