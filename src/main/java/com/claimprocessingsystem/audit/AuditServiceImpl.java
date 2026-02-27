package com.claimprocessingsystem.audit;

import com.claimprocessingsystem.domain.AuditLog;
import com.claimprocessingsystem.domain.Claim;
import com.claimprocessingsystem.domain.enums.ClaimStatus;
import com.claimprocessingsystem.repository.AuditLogRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void logStatusTransition(Claim claim, ClaimStatus fromStatus, ClaimStatus toStatus, String reason) {
        AuditLog auditLog = new AuditLog();
        auditLog.setClaim(claim);
        auditLog.setFromStatus(fromStatus);
        auditLog.setToStatus(toStatus);
        auditLog.setReason(reason);
        auditLog.setChangedAt(OffsetDateTime.now());
        auditLog.setChangedBy(currentUsername());
        auditLogRepository.save(auditLog);
    }

    private String currentUsername() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getName)
                .filter(name -> !"anonymousUser".equals(name))
                .orElse("system");
    }
}
