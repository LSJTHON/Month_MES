package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoutingApiController {

    private final RoutingService routingService;

    @GetMapping("/routings")
    public Map<String,Object> getAllRouting() {

        Map<String,Object> getAllRouting = new HashMap<>();

        getAllRouting.put("data",routingService.findAll());

        return getAllRouting;
    }

    @GetMapping("/routingNumber/{routingNumber}")
    public ResponseEntity<List<RoutingListViewResponse>> getAllRouting(@PathVariable int routingNumber) {
        System.out.println("여기로 오나???" + routingNumber);
        List<RoutingListViewResponse> items = routingService.findRoutingItemsByRoutingNumber(routingNumber);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/routings/{number}")//  삭제
    public ResponseEntity<Void> deleteMaterial(@PathVariable int number) {
        System.out.println("bhhubhububhububhbhubbhubhubhu"+ number);
        routingService.deleteRouting(number);

        return ResponseEntity.ok()
                .build();
    }
}
