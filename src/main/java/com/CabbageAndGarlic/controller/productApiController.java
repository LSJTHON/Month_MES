package com.CabbageAndGarlic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class productApiController {

    @GetMapping("/api/port")
    public ResponseEntity<List<portResponse>> findAllPort(){
        List<portResponse> port = portService.findAll()
                .stream()
                .map(portResponse::new)
                .toList();

        //최종적으로 JSON 형태로 body부분에 port가 들어가게 된다.
        return ResponseEntity.ok()
                .body(port);
    }

}
