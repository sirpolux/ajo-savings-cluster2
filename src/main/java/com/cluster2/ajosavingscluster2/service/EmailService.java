package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
