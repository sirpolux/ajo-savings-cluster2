package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.dto.TransferResponseDto;

public interface WalletService {
     String generateWalletNumber();
     TransferResponseDto transferMoneyToPersonalWallet(Long userId, double amount);

}
