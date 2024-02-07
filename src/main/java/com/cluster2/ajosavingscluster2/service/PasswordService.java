package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.PasswordResetEmailRequest;
import com.cluster2.ajosavingscluster2.dto.PasswordResetNewPasswordRequest;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

public interface PasswordService {
    String resetPasswordRequest(PasswordResetEmailRequest emailRequest)
            throws MessagingException, UnsupportedEncodingException;
    String resetPassword(PasswordResetNewPasswordRequest newPasswordRequest, String token);
}

