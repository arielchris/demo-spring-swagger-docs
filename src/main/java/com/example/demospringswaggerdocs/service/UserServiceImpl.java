package com.example.demospringswaggerdocs.service;

import com.example.demospringswaggerdocs.domain.Users;
import com.example.demospringswaggerdocs.domain.request.UserRequest;
import com.example.demospringswaggerdocs.domain.response.ApiResponse;
import com.example.demospringswaggerdocs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Users> getAllUser() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public Users createUser(UserRequest userRequest) {
        Users users = Users.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .build();
        return userRepository.save(users);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users updateUser(Long id, UserRequest userRequest) {
        Users users = userRepository.findById(id).orElse(null);

        if(ObjectUtils.isEmpty(users)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.build(101, "user id not found"));
            throw new RuntimeException();
        }

        users.setUsername(userRequest.getUsername());
        users.setPassword(userRequest.getPassword());
        users.setFirstName(userRequest.getFirstName());
        users.setLastName(userRequest.getLastName());
        users.setEmail(userRequest.getEmail());

        return userRepository.save(users);
    }

    @Override
    public boolean isEmailAlreadyTaken(String email) {
        return userRepository.countByEmail(email) > 0;
    }

    @Override
    public boolean isUsernameAlreadyTaken(String username) {
        return userRepository.countByUsername(username) > 0;
    }
}
