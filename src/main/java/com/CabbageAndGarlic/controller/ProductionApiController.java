package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionApiController {
    private final ProductionPlanService productionPlanService;
    private final OrderService orderService;
    private final DataService dataService;

    @GetMapping(value = "/plan")
    public Map<String,Object> production(@RequestBody String text) throws ParseException {
        ProductionPlan productionPlan = productionPlanService.findProductionPlan(text);

        Map<String,Object> Orders = new HashMap<>();
        Orders.put("data", orderService.getOrder(productionPlan));


        return Orders;
    }

    @GetMapping(value = "production/orders?date={date}")
    public Map<String,Object> productionOrders(@PathVariable String date) throws ParseException {
        Map<String,Object> Orders = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        Orders.put("date", d);
        return Orders;
    }

}
