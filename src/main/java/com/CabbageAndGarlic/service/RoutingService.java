package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddRoutingRequest;
import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import com.CabbageAndGarlic.repository.ProcessManageRepository;
import com.CabbageAndGarlic.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final ProcessManageRepository processManageRepository;

    public List<Routing> findAll() {
        return routingRepository.findAll();
    }

    public List<Routing> findAllDistinctByProductName() {
        return routingRepository.findAll().stream()
                .filter(routing -> routing.getRoutingProductName() != null)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                routing -> routing.getRoutingProductName().getProductName(),
                                routing -> routing,
                                (existing, replacement) -> existing
                        ),
                        map -> map.values().stream().collect(Collectors.toList())
                ));
    }

    public List<RoutingListViewResponse> findRoutingItemsByProductName(String productName) {

        System.out.println("서비스에 들어온 number" + productName);
        String routingProduct = routingRepository.findProductNameByProcessNumber(productName);

        System.out.println("그래서 " + productName +"의 제품명은? " + routingProduct );
        List<Routing> routings = routingRepository.findByProductName(routingProduct);
        return routings.stream()
                .map(RoutingListViewResponse::new)
                .collect(Collectors.toList());
    }


//    public List<RoutingListViewResponse> findRoutingItemsByRoutingNumber(int routingNumber) {
//        List<Routing> routings = routingRepository.findByNumber(routingNumber);
//        List<RoutingListViewResponse> resultList = new ArrayList<>();
//        for (Routing routing : routings) {
//            resultList.add(new RoutingListViewResponse(routing));
//        }
//        return resultList;
//    }

    public void saveRouting(AddRoutingRequest routingDto) {
        String productName = routingDto.getRoutingProductName();
        List<String> listRouting = routingDto.getProcessName();

        Product product = productRepository.findByProductName(productName);

        List<ProcessManagement> processManagementList = new ArrayList<>();
        for (String processName : listRouting) {
            ProcessManagement processManagement = processManageRepository.findByProcessName(processName);
            if (processManagement != null) {
                processManagementList.add(processManagement);
            }
        }

        int allCycleTime = 0; // 필요에 따라 전체 공정 시간을 계산하는 로직 추가
        for (ProcessManagement processManagement : processManagementList) {
            Routing routing = Routing.builder()
                    .routingNumber(processManagement)
                    .routingProductName(product)
                    .allCycleTime(allCycleTime)
                    .build();
            routingRepository.save(routing);
        }
    }

    public void deleteRouting(int number) {
        routingRepository.deleteById(number);
    }
}
