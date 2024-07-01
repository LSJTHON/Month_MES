package com.CabbageAndGarlic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inoutbound")
public class InOutBoundController {

    @GetMapping
    public String getInOutBoundPage(Model model) {
        return "InOutBound/inOutBound";
    }
}
