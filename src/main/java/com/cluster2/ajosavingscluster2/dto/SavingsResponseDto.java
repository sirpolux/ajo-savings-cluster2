package com.cluster2.ajosavingscluster2.dto;

import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.GroupWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import com.cluster2.ajosavingscluster2.model.SafeLockWallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsResponseDto {
    private List<GlobalWallet> globalWallets;
    private List<GroupWallet> groupWallets;
    private List<PersonalWallet> personalWallets;
    private List<SafeLockWallet> safeLockWallets;
}
