package com.example.spring_rest_api_session_java7.service.impl;

import com.example.spring_rest_api_session_java7.dto.request.RegisterRequest;
import com.example.spring_rest_api_session_java7.dto.response.RegisterResponse;
import com.example.spring_rest_api_session_java7.entities.Role;
import com.example.spring_rest_api_session_java7.entities.User;
import com.example.spring_rest_api_session_java7.repository.RoleRepository;
import com.example.spring_rest_api_session_java7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public RegisterResponse create(RegisterRequest request) {
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }


    private User mapToEntity(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setPassword(request.getPassword());
        return user;
    }

    private RegisterResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }
        RegisterResponse response = new RegisterResponse();
        if (user.getId() != null) {
            response.setId(String.valueOf(user.getId()));
        }
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        return response;
    }
    private List<RegisterResponse> getResponseList(List<User> users){
        List<RegisterResponse> registerResponses = new ArrayList<>();
        for (User user:users) {
            registerResponses.add(mapToResponse(user));


        }
        return registerResponses;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found email"));
    }
    public List<RegisterResponse> getAllUsers(){
        return getResponseList(userRepository.findAll());
    }
    public RegisterResponse getUserById(Long id){
        return mapToResponse(userRepository.getById(id));
    }
    public RegisterResponse updateUser(Long id,RegisterRequest userRequest){
        User user = userRepository.findById(id).get();
        if (userRequest.getEmail() != null)
            user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null)
            user.setFirstName(userRequest.getFirstName());
        if (userRequest.getFirstName() != null)
            user.setPassword(userRequest.getPassword());
        return mapToResponse(user);

    }
    public RegisterResponse deleteUser(Long id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return mapToResponse(user);
    }
    public RegisterResponse addRole(Long roleId, Long userId) throws IOException {
        User user = userRepository.findById(userId).get();
        Role role = roleRepository.findById(roleId).get();
        if (role.getRoleName().equalsIgnoreCase("Admin")){
            throw new IOException("Bir ele Admin bolot");

        }
        user.getRoles().add(role);
        role.getUsers().add(user);

        userRepository.save(user);
        roleRepository.save(role);

        return mapToResponse(user);
    }
    @PostConstruct
    public void initMethod(){
        Role role1 = new Role();
        role1.setRoleName("Admin");

        Role role2 = new Role();
        role2.setRoleName("Instructor");

        Role role3 = new Role();
        role3.setRoleName("Student");

        RegisterRequest request = new RegisterRequest();
        request.setEmail("esen@gmail.com");
        request.setPassword(passwordEncoder.encode("1234"));
        request.setFirstName("Esen");

        User user2 = mapToEntity(request);

        user2.setRoles(Arrays.asList(role1));
        role1.setUsers(Arrays.asList(user2));

        userRepository.save(user2);
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }
}
