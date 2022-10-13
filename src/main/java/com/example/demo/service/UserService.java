package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.exception.ResponseType;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class UserService {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserEntity getUserById(int userId) {
        UserEntity user = getUserRepository().findById(userId).<RuntimeException>orElseThrow(() -> {
            throw new ApiException(ResponseType.USER_NOT_FOUND);
        });

        UserEntity u = UserEntity.builder()
                .userName(user.getUserName())
                .id(user.getId())
                .enabled(user.getEnabled())
                .build();
        return u;
    }

    @Transactional
    public void actDeatUser(int userId, boolean isEnabled) {
        getUserRepository().actDeactUser(isEnabled, userId);
    }

    public String createToken(int userId) {
//        try {
////            return getJwtUtil().generateToken(userId, "ADMIN");
//        } catch (JoseException je) {
//            log.error(je.getMessage());
//            throw new ApiException(ResponseType.JWT_ERROR);
//        }
        return null;
    }

    public UserEntity getUserByUsername(String userName) {
        return getUserRepository().findByUserName(userName)
                .orElseThrow(() -> new ApiException(ResponseType.USER_NOT_FOUND));
    }
}
