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

    private final InOutBoundService inOutBoundService;

    @GetMapping("/inbound")
    public List<InOutBoundDto> getAllInbound() {
        return inOutBoundService.getAllInbounds();
    }

    @PostMapping("/inbound")
    public void addInbound(@RequestBody InOutBoundDto inOutBoundDto) {
        inOutBoundService.addInbound(inOutBoundDto);
    }

    @GetMapping("/outbound")
    public List<InOutBoundDto> getAllOutbound() {
        return inOutBoundService.getAllOutbounds();
    }

    @PostMapping("/outbound")
    public void addOutbound(@RequestBody InOutBoundDto inOutBoundDto) {
        inOutBoundService.addOutbound(inOutBoundDto);
    }

    @GetMapping("/all")
    public List<InOutBoundDto> getAllTransactions() {
        return inOutBoundService.getAllTransactions();
    }
}
