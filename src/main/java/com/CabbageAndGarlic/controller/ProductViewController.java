package com.CabbageAndGarlic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductViewController {

    @GetMapping(value = "/product")//완제품
    public String product(Model model) {

        return "product/ProductView";
    }


    @GetMapping(value = "/material")//자재
    public String material(Model model) {

        return "product/MaterialView";
    }

    @GetMapping(value = "/process")//공정
    public String processManage(Model model) {

        return "processManagement/ProcessManageView";
    }

    @GetMapping(value = "/routing")//라우팅
    public String routing(Model model) {

        return "routing/RoutingView";
    }

}
