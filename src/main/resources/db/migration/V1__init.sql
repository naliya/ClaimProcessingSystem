CREATE TABLE claims (
    id BIGSERIAL PRIMARY KEY,
    claimant_name VARCHAR(150) NOT NULL,
    policy_no VARCHAR(100) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMPTZ,
    updated_by VARCHAR(100),
    version BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_claims_policy_no ON claims(policy_no);
CREATE INDEX idx_claims_status ON claims(status);

CREATE TABLE audit_logs (
    id BIGSERIAL PRIMARY KEY,
    claim_id BIGINT NOT NULL,
    from_status VARCHAR(20),
    to_status VARCHAR(20) NOT NULL,
    changed_by VARCHAR(100) NOT NULL,
    changed_at TIMESTAMPTZ NOT NULL,
    reason VARCHAR(500),
    CONSTRAINT fk_audit_logs_claim FOREIGN KEY (claim_id) REFERENCES claims(id)
);

CREATE INDEX idx_audit_logs_claim_id ON audit_logs(claim_id);
CREATE INDEX idx_audit_logs_changed_at ON audit_logs(changed_at);
