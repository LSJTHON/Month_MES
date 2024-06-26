package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.AddRoutingRequest;
import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.service.ProcessManageService;
import com.CabbageAndGarlic.service.ProductService;
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
    private final ProductService productService;
    private final ProcessManageService processManageService;
    private final ProductApiController productApiController;

//    @GetMapping("/routings")
//    public Map<String, Object> getAllRouting() {
//        Map<String, Object> getAllRouting = new HashMap<>();
//        getAllRouting.put("data", routingService.findAll());
//        return getAllRouting;
//    }

    @GetMapping("/routings")
    public Map<String, Object> getAllRouting() {
        Map<String, Object> getAllRouting = new HashMap<>();
        getAllRouting.put("data", routingService.findAllDistinctByProductName());
        return getAllRouting;
    }

    @GetMapping("/routingProducts")
    public Map<String, Object> getAllProducts() {
        Map<String, Object> getAllProducts = new HashMap<>();
        getAllProducts.put("data", productService.findAll());
        return getAllProducts;
    }

    @GetMapping("/routingProcess")
    public Map<String, Object> getAllProcess() {
        Map<String, Object> getAllProcess = new HashMap<>();
        getAllProcess.put("data", processManageService.findAll());
        return getAllProcess;
    }

//    @GetMapping("/routingNumber/{routingNumber}")
//    public ResponseEntity<List<RoutingListViewResponse>> getAllRouting(@PathVariable int routingNumber) {
//        List<RoutingListViewResponse> items = routingService.findRoutingItemsByRoutingNumber(routingNumber);
//        return ResponseEntity.ok(items);
//    }

    @GetMapping("/routingNumber/{product}")
    public ResponseEntity<List<RoutingListViewResponse>> getAllRouting(@PathVariable String product) {
        System.out.println(product+"dwidjiowjqiodjwqoidjioqwjdioq");
        List<RoutingListViewResponse> items = routingService.findRoutingItemsByProductName(product);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/createRouting")
    public String saveRouting(@RequestBody AddRoutingRequest request) {
        System.out.println("Received routingDto: " + request);
        routingService.saveRouting(request);
        return "정상적으로 등록했습니다.";
    }

    @DeleteMapping("/routings/{number}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable int number) {
        routingService.deleteRouting(number);
        return ResponseEntity.ok().build();
    }
}
