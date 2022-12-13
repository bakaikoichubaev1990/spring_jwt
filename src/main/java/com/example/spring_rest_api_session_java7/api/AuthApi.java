package com.example.spring_rest_api_session_java7.api;

import com.example.spring_rest_api_session_java7.converter.LoginConverter;
import com.example.spring_rest_api_session_java7.dto.request.RegisterRequest;
import com.example.spring_rest_api_session_java7.dto.response.LoginResponse;
import com.example.spring_rest_api_session_java7.dto.response.RegisterResponse;
import com.example.spring_rest_api_session_java7.entities.User;
import com.example.spring_rest_api_session_java7.repository.UserRepository;
import com.example.spring_rest_api_session_java7.security.ValidationExceptionType;
import com.example.spring_rest_api_session_java7.security.jwt.JwtTokenUtil;
import com.example.spring_rest_api_session_java7.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: Ulansky
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthApi {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword());
            User user = userRepository.finByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginConverter.
                    loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(loginConverter.
                            loginView("", ValidationExceptionType
                                    .LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        return userService.create(request);
    }
}
