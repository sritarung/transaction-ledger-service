package com.sritarun.ledger.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class AccountResponse {
    private String id;
    private String ownerName;
    private String currency;
    private BigDecimal balance;
    private Instant createdAt;
}
