package com.cluster2.ajosavingscluster2.service;

import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.GroupWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import com.cluster2.ajosavingscluster2.model.SafeLockWallet;

import java.util.List;

public interface SavingsService {
    List<GlobalWallet> getAllGlobalWallets();
    List<GroupWallet> getAllGroupWallets();
    List<PersonalWallet> getAllPersonalWallets();
    List<SafeLockWallet> getAllSafeLockWallets();

}
