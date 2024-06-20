package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.service.ProcessManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessManageApiController {
private final ProcessManageService processManageService;

    @GetMapping("/process") // 공정 전체 조회
    public Map<String,Object> getAllProcess() {

        Map<String,Object> getAllProcess = new HashMap<>();

        getAllProcess.put("data",processManageService.findAll());

        return getAllProcess;
    }

}
