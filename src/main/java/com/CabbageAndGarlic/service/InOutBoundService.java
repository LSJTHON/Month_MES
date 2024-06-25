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
        List<InOutBound> inbounds = inOutBoundRepository.findByTransactionType(InOutBound.TransactionType.IN);
        return inbounds.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void addInbound(InOutBoundDto inOutBoundDto) {
        InOutBound inbound = toEntity(inOutBoundDto);
        inbound.setTransactionType(InOutBound.TransactionType.IN);
        inOutBoundRepository.save(inbound);
    }

    public List<InOutBoundDto> getAllOutbounds() {
        List<InOutBound> outbounds = inOutBoundRepository.findByTransactionType(InOutBound.TransactionType.OUT);
        return outbounds.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void addOutbound(InOutBoundDto inOutBoundDto) {
        InOutBound outbound = toEntity(inOutBoundDto);
        outbound.setTransactionType(InOutBound.TransactionType.OUT);
        inOutBoundRepository.save(outbound);
    }

    public List<InOutBoundDto> getAllTransactions() {
        List<InOutBound> transactions = inOutBoundRepository.findAll();
        return transactions.stream().map(this::toDto).collect(Collectors.toList());
    }

    private InOutBoundDto toDto(InOutBound inOutBound) {
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
