package com.example.demospringswaggerdocs.service;

import com.example.demospringswaggerdocs.domain.Users;
import com.example.demospringswaggerdocs.domain.request.UserRequest;

import java.util.List;


public interface UserService {

    public Users getUserById(Long id);

    public List<Users> getAllUser();

    public Users createUser(UserRequest userRequest);

    public void deleteUser(Long id);

    public Users updateUser(Long id, UserRequest userRequest);

    public boolean isEmailAlreadyTaken(String email);

    public boolean isUsernameAlreadyTaken(String username);

}
