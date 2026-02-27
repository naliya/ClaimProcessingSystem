package com.claimprocessingsystem.audit;

import com.claimprocessingsystem.domain.Claim;
import com.claimprocessingsystem.domain.enums.ClaimStatus;

public interface AuditService {

    void logStatusTransition(Claim claim, ClaimStatus fromStatus, ClaimStatus toStatus, String reason);
}
