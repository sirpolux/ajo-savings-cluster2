package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.TransferResponseDto;
import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import com.cluster2.ajosavingscluster2.model.Wallet;
import com.cluster2.ajosavingscluster2.repository.GlobalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.PersonalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.WalletRepository;
import com.cluster2.ajosavingscluster2.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class WalletServiceImplementation implements WalletService {

    private final WalletRepository walletRepository;
    private final GlobalWalletRepository globalWalletRepository;
    private final PersonalWalletRepository personalWalletRepository;
    @Override
    public String generateWalletNumber() {

            Year currentYear = Year.now();
            int min = 1000;
            int max = 9999;

            int randNumber = min + (int)(Math.random() * ((max - min) + 1));

            String year = String.valueOf(currentYear);
            String randomNumber = String.valueOf(randNumber);

            String walletNumber = year + randomNumber;

            // Check if the generated wallet number exists in the database
            while (isDuplicateInDatabase(walletNumber)) {
                randNumber = min + (int)(Math.random() * ((max - min) + 1));
                randomNumber = String.valueOf(randNumber);
                walletNumber = year + randomNumber;
            }
            walletRepository.save(Wallet.builder()
                            .walletNumber(walletNumber)
                    .build());

            return walletNumber;





    }

    private boolean isDuplicateInDatabase(String walletNumber) {
        Optional<Wallet> isWalletNumberAvailable = walletRepository.findByWalletNumber(walletNumber);
        return isWalletNumberAvailable.isPresent();

    }
    @Override
    @Transactional
    public TransferResponseDto transferMoneyToPersonalWallet(Long userId, double amount) {
        GlobalWallet globalWallet = globalWalletRepository.findByUserId(userId);
        if (globalWallet == null) {
            return new TransferResponseDto("Global wallet not found for the user");
        }

        PersonalWallet personalWallet = personalWalletRepository.findByUserId(userId);
        if (personalWallet == null) {
            return new TransferResponseDto("Personal wallet not found for the user");
        }

        if (globalWallet.getWalletBalance().compareTo(BigDecimal.valueOf(amount)) < 0){
            return new TransferResponseDto("Insufficient funds in the global wallet");
        }
        globalWallet.setWalletBalance(globalWallet.getWalletBalance().subtract(BigDecimal.valueOf(amount)));
        personalWallet.setWalletBalance(personalWallet.getWalletBalance().add(BigDecimal.valueOf(amount)));


        globalWalletRepository.save(globalWallet);
        personalWalletRepository.save(personalWallet);

        return new TransferResponseDto("Money transferred successfully");
    }

}
