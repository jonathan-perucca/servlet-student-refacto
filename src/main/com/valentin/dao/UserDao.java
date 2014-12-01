package com.valentin.dao;

import com.valentin.domain.User;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by under on 28/11/14.
 */
public interface UserDao {
    int size();

    User get(Long id);
    User save(User user);
    boolean remove(Long id);

    List<User> getUsers();
}
