package com.example.pos_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreRequestDTO {
    private String merchantId;
    private String storeName;
    private String address;
    private String timezone;
    private String status;
    private BigDecimal taxRate;
    private String currency;
    private String businessHours;
    private String createdBy;
    private String updatedBy;
}
