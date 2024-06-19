package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductionDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.repository.ProductionPlanRepository;
import com.CabbageAndGarlic.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionRestController {
    private final ProductionPlanService productionPlanService;
    private final OrderService orderService;
    private final DataService dataService;

    @PostMapping(value = "/plan")
    public String production(@RequestBody String text, HttpSession session) throws ParseException {
        ProductionPlan productionPlan = productionPlanService.findProductionPlan(text);
        List<Order> order = orderService.getOrder(productionPlan);
        session.setAttribute("orders", order);

        return "Data saved to session.";
    }

    @GetMapping("/api/data")
    public List<ProductionDto> getData(HttpSession session) {

        return dataService.getAllData(orders);
    }

}
