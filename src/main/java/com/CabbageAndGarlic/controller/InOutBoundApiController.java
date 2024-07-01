package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.InOutBoundDto;
import com.CabbageAndGarlic.service.InOutBoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inoutbound")
@RequiredArgsConstructor
public class InOutBoundApiController {

    private final InOutBoundService inOutBoundService; // 입출고 관련 서비스

    @GetMapping("/inbound") // 입고 목록 조회
    public List<InOutBoundDto> getAllInbound() {
        return inOutBoundService.getAllInbounds(); // 모든 입고 정보를 조회하여 반환
    }

    @PostMapping("/inbound") // 입고 정보 추가
    public void addInbound(@RequestBody InOutBoundDto inOutBoundDto) {
        inOutBoundService.addInbound(inOutBoundDto); // 전달받은 입고 정보를 추가
    }

    @GetMapping("/outbound") // 출고 목록 조회
    public List<InOutBoundDto> getAllOutbound() {
        return inOutBoundService.getAllOutbounds(); // 모든 출고 정보를 조회하여 반환
    }

    @PostMapping("/outbound") // 출고 정보 추가
    public void addOutbound(@RequestBody InOutBoundDto inOutBoundDto) {
        inOutBoundService.addOutbound(inOutBoundDto); // 전달받은 출고 정보를 추가
    }

    @GetMapping("/all") // 모든 입출고 목록 조회
    public List<InOutBoundDto> getAllTransactions() {
        return inOutBoundService.getAllTransactions(); // 모든 입출고 정보를 조회하여 반환
    }
}
