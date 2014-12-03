package com.valentin.service;

import com.valentin.domain.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);
    User saveUser(User user);
    User saveUser(String nom, String genre, String Birthday);
    boolean removeUser(Long id);
    List<User> getUsers();
}
