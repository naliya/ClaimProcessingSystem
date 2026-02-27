package com.claimprocessingsystem.repository;

import com.claimprocessingsystem.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
