package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoutingApiController {

    private final RoutingService routingService;

    @GetMapping("/routings") // 완제품조회
    public Map<String,Object> getAllRouting() {

        Map<String,Object> getAllRouting = new HashMap<>();

        getAllRouting.put("data",routingService.findAll());

        return getAllRouting;
    }

    @GetMapping("/routingNumber/{routingNumber}")
    public ResponseEntity<List<RoutingListViewResponse>> getAllRouting(@PathVariable Long routingNumber) {
        System.out.println("여기로 오나???" + routingNumber);
        List<RoutingListViewResponse> items = routingService.findRoutingItemsByRoutingNumber(routingNumber);
        return ResponseEntity.ok(items);
    }
}
