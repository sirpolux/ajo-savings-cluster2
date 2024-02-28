package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.model.Wallet;
import com.cluster2.ajosavingscluster2.repository.WalletRepository;
import com.cluster2.ajosavingscluster2.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Year;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class WalletServiceImplementation implements WalletService {

    private final WalletRepository walletRepository;
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
}
