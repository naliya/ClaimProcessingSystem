package com.claimprocessingsystem.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClaimProcessingWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimProcessingWorker.class);

    @Scheduled(fixedDelayString = "${app.worker.claim-processing-delay-ms:60000}")
    public void processQueuedClaims() {
        LOGGER.debug("Claim processing worker heartbeat");
    }
}
