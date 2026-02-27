package com.claimprocessingsystem.service;

import com.claimprocessingsystem.dto.ClaimCreateRequest;
import com.claimprocessingsystem.dto.ClaimResponse;
import java.util.List;

public interface ClaimService {

    ClaimResponse submitClaim(ClaimCreateRequest request);

    List<ClaimResponse> listClaims();
}
