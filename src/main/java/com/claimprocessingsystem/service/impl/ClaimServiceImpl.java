package com.claimprocessingsystem.service.impl;

import com.claimprocessingsystem.audit.AuditService;
import com.claimprocessingsystem.domain.Claim;
import com.claimprocessingsystem.domain.enums.ClaimStatus;
import com.claimprocessingsystem.dto.ClaimCreateRequest;
import com.claimprocessingsystem.dto.ClaimResponse;
import com.claimprocessingsystem.repository.ClaimRepository;
import com.claimprocessingsystem.service.ClaimService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final AuditService auditService;

    public ClaimServiceImpl(ClaimRepository claimRepository, AuditService auditService) {
        this.claimRepository = claimRepository;
        this.auditService = auditService;
    }

    @Override
    public ClaimResponse submitClaim(ClaimCreateRequest request) {
        Claim claim = new Claim();
        claim.setClaimantName(request.claimantName());
        claim.setPolicyNo(request.policyNo());
        claim.setAmount(request.amount());
        claim.setStatus(ClaimStatus.PENDING);

        Claim saved = claimRepository.save(claim);
        auditService.logStatusTransition(saved, null, ClaimStatus.PENDING, "Claim submitted");
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimResponse> listClaims() {
        return claimRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private ClaimResponse toResponse(Claim claim) {
        return new ClaimResponse(
                claim.getId(),
                claim.getClaimantName(),
                claim.getPolicyNo(),
                claim.getAmount(),
                claim.getStatus(),
                claim.getCreatedAt(),
                claim.getCreatedBy(),
                claim.getUpdatedAt(),
                claim.getUpdatedBy(),
                claim.getVersion()
        );
    }
}
