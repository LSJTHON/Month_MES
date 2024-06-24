package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    //수주정보 전부 가져오기
    @GetMapping("/orders")
    public Map<String,Object> getAllOrders() {

        Map<String,Object> getAllOrders = new HashMap<>();

        getAllOrders.put("data",orderService.findAll());

        return getAllOrders;
    }

    //캘린더에서 수주정보 모두 보여주기
    @GetMapping("/orderCalendar")
    public List<Order> getCalendarOrder() {
        return orderService.findAll();
    }



    //수주 등록하기
    @PostMapping("/createOrder")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "수주를 정상적으로 등록했습니다.";
    }


    //상품상세보기
    @GetMapping("/orderItems/{orderNumber}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
        List<OrderItem> items = orderService.findOrderItemsByOrderNumber(orderNumber);
        return ResponseEntity.ok(items);
    }


    //상품 작업상태 임시 수정 메소드
    @PutMapping("/testComplete/{orderNumber}")
    public void testComplete(@PathVariable Long orderNumber) {
        orderService.testComplete(orderNumber);
    }



    @PostMapping("/uploadExcel")
    public ResponseEntity<Map<String, Object>> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<Map<String, Object>> products = new ArrayList<>();
        try (Workbook workbook = org.apache.poi.ss.usermodel.WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // 첫 번째 행은 헤더이므로 건너뜀
                if (row.getRowNum() == 0) {
                    continue;
                }

                // 품목명과 수량 셀이 빈칸이 아닌 경우에만 처리
                if (row.getCell(2) != null && row.getCell(3) != null) {
                    String productName = row.getCell(2).getStringCellValue().trim();
                    if (!productName.isEmpty() && row.getCell(3).getCellType() == CellType.NUMERIC) {
                        Map<String, Object> product = new HashMap<>();
                        product.put("client", row.getCell(0).getStringCellValue());
                        product.put("phoneNumber", row.getCell(1).getStringCellValue());
                        product.put("productName", productName);
                        product.put("quantity", (int) row.getCell(3).getNumericCellValue());
                        products.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        return ResponseEntity.ok(response);
    }


}
