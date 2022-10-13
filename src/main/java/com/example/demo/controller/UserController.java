package com.example.demo.controller;

import com.example.demo.model.UserCredentialsRequest;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.request.ActDeatUser;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(
            UserService userService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(
            path = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({"USER"})
    public ResponseEntity<?> getUser(
            @PathVariable(value = "userId") int userId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getUser");
        log.info("PathVars|userId={}", userId);
        try {
            UserEntity user = userService.getUserById(userId);
            log.info("Res|{}", user.toString());
            return ResponseEntity.ok(user);
        } finally {
            log.info("Completed|getUser");
            log.info("ProcessingTime|{}ms", System.currentTimeMillis() - startTime);
        }
    }

    @PatchMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actDeactUser(
            @Valid @RequestBody ActDeatUser actDeatUser
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|actDeactUser");
        log.info("ReqBody|{}", actDeatUser.toString());
        try {
            userService.actDeatUser(actDeatUser.getUserId(), actDeatUser.getIsEnabled());
            log.info("Res|");
            return ResponseEntity.ok().build();
        } finally {
            log.info("Completed|actDeactUser");
            log.info("ProcessingTime|{}ms", System.currentTimeMillis() - startTime);
        }
    }


    @GetMapping(
            path = "/auth/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({"ADMIN", "SUPER_ADMIN"})
    public ResponseEntity<?> authUser(
            @PathVariable(value = "userId") int userId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|authUser");
        log.info("PathVars|userId={}", userId);
        try {
            String token = userService.createToken(userId);
            log.info("Res|{}", token);
            return ResponseEntity.ok(token);
        } finally {
            log.info("Completed|authUser");
            log.info("ProcessingTime|{}ms", System.currentTimeMillis() - startTime);
        }
    }


    @PostMapping(
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> doLogin(
            @Valid @RequestBody UserCredentialsRequest userCredentialsRequest
    ) throws JoseException {
        System.out.println(BCrypt.hashpw("abc", BCrypt.gensalt()));
        long startTime = System.currentTimeMillis();
        log.info("Initiating|doLogin");
        log.info("ReqBody|{}", userCredentialsRequest.toString());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCredentialsRequest.getUsername(),
                            userCredentialsRequest.getPassword()
                    )
            );

            UserEntity user = userService.getUserByUsername(userCredentialsRequest.getUsername());
            String token = jwtUtil.generateToken(user.getId(), user.getUserName(), user.getRole());
            log.info("Res|{}", token);
            return ResponseEntity.ok(token);
        } finally {
            log.info("Completed|doLogin");
            log.info("ProcessingTime|{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
