package com.CabbageAndGarlic.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping(value = "/test")
    public String reply(Model model) {

        return "mesView";
    }

}
