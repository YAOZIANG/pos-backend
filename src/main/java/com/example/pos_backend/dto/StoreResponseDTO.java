package com.example.pos_backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponseDTO {
    private String id;
    private String merchantId;
    private String storeName;
    private String address;
    private String timezone;
    private String status;
    private BigDecimal taxRate;
    private String currency;
    private String businessHours;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
