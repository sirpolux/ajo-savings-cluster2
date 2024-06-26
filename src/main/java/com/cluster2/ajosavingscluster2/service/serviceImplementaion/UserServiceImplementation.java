package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.configuration.JwtService;
import com.cluster2.ajosavingscluster2.dto.*;
import com.cluster2.ajosavingscluster2.enums.AccountType;
import com.cluster2.ajosavingscluster2.enums.Role;
import com.cluster2.ajosavingscluster2.enums.TransactionStatus;
import com.cluster2.ajosavingscluster2.model.GlobalWallet;
import com.cluster2.ajosavingscluster2.model.PersonalWallet;
import com.cluster2.ajosavingscluster2.model.Transaction;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.GlobalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.PersonalWalletRepository;
import com.cluster2.ajosavingscluster2.repository.TransactionRepository;
import com.cluster2.ajosavingscluster2.repository.UserRepository;
import com.cluster2.ajosavingscluster2.service.EmailService;
import com.cluster2.ajosavingscluster2.service.UserService;
import com.cluster2.ajosavingscluster2.service.WalletService;
import com.cluster2.ajosavingscluster2.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImplementation implements UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final WalletService walletService;
    private final GlobalWalletRepository globalWalletRepository;
    private final PersonalWalletRepository personalWalletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ApiResponse> signUp(UserRequest userRequest) {
        System.out.println("path2");
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(userRequest.getUsername());
        if (optionalUser.isPresent()) {
            return new ResponseEntity(ApiResponse.builder()
                    .responseCode(UserUtils.USER_EXISTS_CODE)
                    .responseMessage(UserUtils.USER_EXISTS_MESSAGE)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getEmail())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.ROLE_USER)
                .confirmPassword(passwordEncoder.encode(userRequest.getConfirmPassword()))
                .build();

        User savedUser = userRepository.save(newUser);
        String globalWallet = walletService.generateWalletNumber();
        String personalWallet = walletService.generateWalletNumber();

        GlobalWallet globalWallet1 = new GlobalWallet(savedUser);
        globalWallet1.setWalletNumber(globalWallet);
        globalWalletRepository.save(globalWallet1);

        PersonalWallet personalWallet1 = new PersonalWallet(savedUser);
        personalWallet1.setWalletNumber(personalWallet);
        personalWalletRepository.save(personalWallet1);


        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("USER CREATION")
                .messageBody("Dear: " + savedUser.getFirstName() + " " + savedUser.getLastName() + "Your Account as been successfully created")
                .build();
        emailService.sendEmailAlert(emailDetails);


        return new ResponseEntity(ApiResponse.builder()
                .responseCode(UserUtils.USER_CREATION_SUCCESS_CODE)
                .responseMessage(UserUtils.USER_CREATION_SUCCESS_MESSAGE)
                .data("New account created for \n"+userRequest.getFirstName())
                .build(), HttpStatus.OK);
    }

    @Override
    public ApiResponse login(LoginRequest loginRequest) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            log.info("Generating token for {}", userDetails.getUsername());
            String token = jwtService.generateToken(userDetails);
            log.info("JWT Token: {}", token);

            Authentication authentication = null;
            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );

        } catch (AuthenticationException e) {
            return ApiResponse.builder()
                    .responseCode(UserUtils.LOGIN_FAILURE_CODE)
                    .responseMessage(UserUtils.LOGIN_FAILURE_MESSAGE)
                    .build();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ApiResponse.builder()
                .responseCode(UserUtils.LOGIN_SUCCESS_CODE)
                .responseMessage(token)
                .fName(((User)userDetails).getFirstName())
                .build();

    }

    @Override
    public ApiResponse credit(CreditDebitWalletRequest creditDebitWalletRequest) {
        GlobalWallet globalWallet = globalWalletRepository.findByUser(getUser());
        Transaction transaction = new Transaction();
        globalWallet.setWalletBalance(globalWallet.getWalletBalance().add(creditDebitWalletRequest.getAmount()));
        globalWalletRepository.save(globalWallet);
        transaction.setTransactionAmount(creditDebitWalletRequest.getAmount());
        transaction.setReceiverWalletNumber(globalWallet.getWalletNumber());
        transaction.setReceiverAccountType(AccountType.PERSONAL_WALLET);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setDescription("PERSONAL ACCOUNT FUNDING");
        transactionRepository.save(transaction);
        return ApiResponse.builder()
                .responseCode(UserUtils.WALLET_CREDITED_SUCCESS_CODE)
                .responseMessage(UserUtils.WALLET_CREDITED_SUCCESS_MESSAGE)
                .walletInfo(WalletInfo.builder()
                        .walletBalance(String.valueOf(globalWallet.getWalletBalance()))
                        .build())
                .build();
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
