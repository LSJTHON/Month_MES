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

    public List<Routing> findAllDistinctByProductName() { // 원하는 정보 값 가져오는 로직
        return routingRepository.findAll().stream()
                .filter(routing -> routing.getRoutingProductName() != null)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                routing -> routing.getRoutingProductName().getProductName(),
                                routing -> routing,
                                (existing, replacement) -> existing
                        ),
                        map -> new ArrayList<>(map.values())
                ));
    }

    public List<RoutingListViewResponse> findRoutingItemsByProductName(String product) {

        List<Routing> routings = routingRepository.findByProductName(product);
        return routings.stream()
                .map(RoutingListViewResponse::new)
                .collect(Collectors.toList());
    }


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
        for (ProcessManagement processManagement : processManagementList) {

            int allCycleTime = 0; // 필요에 따라 전체 공정 시간을 계산하는 로직 추가


            for (ProcessManagement processManagement2 : processManagementList) {
                System.out.println(processManagement2.getCycleHour()+"이게 뭐가 들어오지");
                allCycleTime += processManagement2.getCycleHour();
                System.out.println(allCycleTime+"이게 사이클 타임이다 이말이야");

            }


            Routing routing = Routing.builder()
                    .routingNumber(processManagement)
                    .routingProductName(product)
                    .allCycleTime(allCycleTime)
                    .build();
            routingRepository.save(routing);// 최종 세이브 라우팅 정보 저장 로직
        }
    }

    public void deleteRouting(String productName) {
        routingRepository.deleteByRoutingNumberProductName(productName);
    }
}
