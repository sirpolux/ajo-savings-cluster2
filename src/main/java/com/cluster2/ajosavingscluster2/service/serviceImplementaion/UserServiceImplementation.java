package com.cluster2.ajosavingscluster2.service.serviceImplementaion;

import com.cluster2.ajosavingscluster2.dto.ApiResponse;
import com.cluster2.ajosavingscluster2.dto.EmailDetails;
import com.cluster2.ajosavingscluster2.dto.UserRequest;
import com.cluster2.ajosavingscluster2.enums.Role;
import com.cluster2.ajosavingscluster2.model.User;
import com.cluster2.ajosavingscluster2.repository.UserRepository;
import com.cluster2.ajosavingscluster2.service.EmailService;
import com.cluster2.ajosavingscluster2.service.UserService;
import com.cluster2.ajosavingscluster2.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    @Override
    public ApiResponse signUp (UserRequest userRequest) {
        System.out.println("path2");
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(userRequest.getUsername());
            if(optionalUser.isPresent()){
                return ApiResponse.builder()
                        .responseCode(UserUtils.USER_EXISTS_CODE)
                        .responseMessage(UserUtils.USER_EXISTS_MESSAGE)
                        .build();
            }
        User newUser =User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getEmail())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .role(Role.ROLE_USER)
                .ConfirmPassword(userRequest.getConfirmPassword())
                .build();

            User savedUser = userRepository.save(newUser);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("USER CREATION")
                .messageBody("Dear: " + savedUser.getFirstName() + " " + savedUser.getLastName() + "Your Account as been successfully created")
                .build();
        emailService.sendEmailAlert(emailDetails);


            return ApiResponse.builder()
                    .responseCode(UserUtils.USER_CREATION_SUCCESS_CODE)
                    .responseMessage(UserUtils.USER_CREATION_SUCCESS_MESSAGE)
                    .build();
        }

    }

