package com.claimprocessingsystem.dto;

import com.claimprocessingsystem.domain.enums.ClaimStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ClaimResponse(
        Long id,
        String claimantName,
        String policyNo,
        BigDecimal amount,
        ClaimStatus status,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy,
        Long version
) {
}
