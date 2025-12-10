package com.sritarun.ledger.api.dto;

import com.sritarun.ledger.domain.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class TransactionResponse {
    private String id;
    private String accountId;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private Instant createdAt;
}
