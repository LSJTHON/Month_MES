package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.service.MaterialService;
import com.CabbageAndGarlic.service.ProductService;
import com.CabbageAndGarlic.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoutingApiController {

    private final RoutingService routingService;

    @GetMapping("/routings") // 완제품조회
    public Map<String,Object> getAllRoutings() {

        Map<String,Object> getAllRoutings = new HashMap<>();

        getAllRoutings.put("data",routingService.findAll());

        return getAllRoutings;
    }

}
