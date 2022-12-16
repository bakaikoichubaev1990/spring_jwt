package com.example.spring_rest_api_session_java7.api;

import com.example.spring_rest_api_session_java7.api.converter.login.LoginConverter;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt/")
public class AuthApi {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
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

    @PostMapping("registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        return userService.create(request);
    }

    @GetMapping("getAllUser")
    @PreAuthorize("isAuthenticated()")
    public List<RegisterResponse> getAllUser(){
    return userService.getAllUsers();
    }

    @GetMapping("getUserById/{id}")
    @PreAuthorize("isAuthenticated()")
    public RegisterResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PutMapping("updateUser/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public RegisterResponse updateUser(@PathVariable Long id, @RequestBody RegisterRequest request){
        return userService.updateUser(id,request);

    }
    @DeleteMapping ("deleteUser/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public RegisterResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);

    }
    @PostMapping("addRole/{roleId}/{userId}/")
    @PreAuthorize("hasAuthority('Admin')")
    public RegisterResponse addRole(@PathVariable("roleId") Long roleId, @PathVariable("userId") Long userId) throws IOException {
        return userService.addRole(roleId, userId);
    }
}
