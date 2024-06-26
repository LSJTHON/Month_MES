package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.InOutBoundDto;
import com.CabbageAndGarlic.entity.InOutBound;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.repository.InOutBoundRepository;
import com.CabbageAndGarlic.repository.MaterialRepository;
import com.CabbageAndGarlic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InOutBoundService {

    private final InOutBoundRepository inOutBoundRepository;
    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;

    public List<InOutBoundDto> getAllInbounds() {
        // 입고된 모든 트랜잭션 조회
        List<InOutBound> inbounds = inOutBoundRepository.findByTransactionType(InOutBound.TransactionType.IN);
        return inbounds.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void addInbound(InOutBoundDto inOutBoundDto) {
        // 입고 트랜잭션 추가
        InOutBound inbound = toEntity(inOutBoundDto);
        inbound.setTransactionType(InOutBound.TransactionType.IN);
        inOutBoundRepository.save(inbound);
    }

    public List<InOutBoundDto> getAllOutbounds() {
        // 출고된 모든 트랜잭션 조회
        List<InOutBound> outbounds = inOutBoundRepository.findByTransactionType(InOutBound.TransactionType.OUT);
        return outbounds.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void addOutbound(InOutBoundDto inOutBoundDto) {
        // 출고 트랜잭션 추가
        InOutBound outbound = toEntity(inOutBoundDto);
        outbound.setTransactionType(InOutBound.TransactionType.OUT);
        inOutBoundRepository.save(outbound);
    }

    public List<InOutBoundDto> getAllTransactions() {
        // 모든 트랜잭션 조회
        List<InOutBound> transactions = inOutBoundRepository.findAll();
        return transactions.stream().map(this::toDto).collect(Collectors.toList());
    }

    private InOutBoundDto toDto(InOutBound inOutBound) {
        // 엔티티를 DTO로 변환
        return InOutBoundDto.builder()
                .materialCode(inOutBound.getMaterialCode() != null ? inOutBound.getMaterialCode().getMaterialCode() : null)
                .productCode(inOutBound.getProductCode() != null ? inOutBound.getProductCode().getProductCode() : null)
                .materialName(inOutBound.getMaterialCode() != null ? inOutBound.getMaterialCode().getMaterialName() : null)
                .productName(inOutBound.getProductCode() != null ? inOutBound.getProductCode().getProductName() : null)
                .transactionDate(inOutBound.getInoutBoundDate())
                .transactionType(inOutBound.getTransactionType().name())
                .quantity(inOutBound.getQuantity())
                .manager(inOutBound.getManager())
                .build();
    }

    private InOutBound toEntity(InOutBoundDto inOutBoundDto) {
        // DTO를 엔티티로 변환
        Material material = null;
        if (inOutBoundDto.getMaterialCode() != null) {
            material = materialRepository.findById(inOutBoundDto.getMaterialCode())
                    .orElseThrow(() -> new RuntimeException("Material not found"));
        }

        Product product = null;
        if (inOutBoundDto.getProductCode() != null) {
            product = productRepository.findById(inOutBoundDto.getProductCode())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }

        return InOutBound.builder()
                .materialCode(material)
                .productCode(product)
                .inoutBoundDate(inOutBoundDto.getTransactionDate())
                .quantity(inOutBoundDto.getQuantity())
                .manager(inOutBoundDto.getManager())
                .build();
    }
}
