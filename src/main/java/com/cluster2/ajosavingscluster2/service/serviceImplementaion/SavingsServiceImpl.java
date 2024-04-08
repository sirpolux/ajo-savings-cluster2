package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.GroupWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;

import com.cluster2.ajosavingscluster2.model.SafeLockWallet;
import com.cluster2.ajosavingscluster2.repository.GlobalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.GroupWalletRepository;
import com.cluster2.ajosavingscluster2.repository.PersonalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.SafeLockWalletRepository;
import com.cluster2.ajosavingscluster2.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SavingsServiceImpl implements SavingsService {
    private final GlobalWalletRepository globalWalletRepository;
    private final GroupWalletRepository groupWalletRepository;
    private final PersonalWalletRepository personalWalletRepository;
    private final SafeLockWalletRepository safeLockWalletRepository;

    @Autowired
    public SavingsServiceImpl(GlobalWalletRepository globalWalletRepository,
                              GroupWalletRepository groupWalletRepository,
                              PersonalWalletRepository personalWalletRepository,
                              SafeLockWalletRepository safeLockWalletRepository) {
        this.globalWalletRepository = globalWalletRepository;
        this.groupWalletRepository = groupWalletRepository;
        this.personalWalletRepository = personalWalletRepository;
        this.safeLockWalletRepository = safeLockWalletRepository;
    }

    @Override
    public List<GlobalWallet> getAllGlobalWallets() {
        return globalWalletRepository.findAll();
    }

    @Override
    public List<GroupWallet> getAllGroupWallets() {
        return groupWalletRepository.findAll();
    }

    @Override
    public List<PersonalWallet> getAllPersonalWallets() {
        return personalWalletRepository.findAll();
    }

    @Override
    public List<SafeLockWallet> getAllSafeLockWallets() {
        return safeLockWalletRepository.findAll();
    }
}
