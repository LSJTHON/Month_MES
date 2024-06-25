package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {

//    private final OrderService orderService;
//
//    /**
//     * PurchaseOrderController 생성자.
//     *
//     * @param orderService 수주 관리를 위한 서비스.
//     */
//    @Autowired
//    public PurchaseOrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    /**
//     * 발주 페이지를 반환합니다.
//     *
//     * @param model 모델 객체에 수주 데이터를 추가합니다.
//     * @return 발주 페이지 뷰 이름.
//     */
//    @GetMapping
//    public String getPurchaseOrderPage(Model model) {
//        List<Order> orders = orderService.findAll();
//        model.addAttribute("orders", orders);
//        return "/Purchase/Purchase";
//    }
}
