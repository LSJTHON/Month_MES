package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.AddProcessManageRequest;
import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.service.ProcessManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createProcess")// 공정 저장
    public String saveProduct(@RequestBody AddProcessManageRequest addProcessManageRequest) {
        System.out.println(addProcessManageRequest.getProcessNumber()+"들어오니?");
        processManageService.saveProcess(addProcessManageRequest);
        return "등록 성공";
    }
    @DeleteMapping("/process/{processNumber}")// 공정 삭제
    public ResponseEntity<Void> deleteProcess(@PathVariable String processNumber) {
        processManageService.deleteProcess(processNumber);

        return ResponseEntity.ok()
                .build();
    }

}
