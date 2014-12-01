package com.valentin.service;

import com.valentin.domain.User;

public interface UserService {

    public User getUser(Long id);
    public User saveUser(String nom, String genre, String Birthday);
    public boolean removeUser(Long id);
}
