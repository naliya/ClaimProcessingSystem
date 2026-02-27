package com.claimprocessingsystem.controller;

import com.claimprocessingsystem.dto.ClaimCreateRequest;
import com.claimprocessingsystem.dto.ClaimResponse;
import com.claimprocessingsystem.service.ClaimService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse submitClaim(@Valid @RequestBody ClaimCreateRequest request) {
        return claimService.submitClaim(request);
    }

    @GetMapping
    public List<ClaimResponse> listClaims() {
        return claimService.listClaims();
    }
}
