package com.valentin.dao.impl;

import com.valentin.dao.UserDao;
import com.valentin.domain.User;
import com.valentin.store.Store;
import com.valentin.store.impl.ListStoreImpl;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private Store<User, Long> userStore;

    public UserDaoImpl(Store userStore) {
        this.userStore = userStore;
    }

    @Override
    public int size() {
        return userStore.count();
    }

    @Override
    public User get(Long id) {
        return userStore.get(id);
    }

    @Override
    public User save(User user) {
        return userStore.save(user);
    }

    @Override
    public boolean remove(Long id) {
        return userStore.delete(id);
    }

    @Override
    public List<User> getUsers() {
        return userStore.getAll();
    }

    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if(instance == null) {
            instance = new UserDaoImpl(ListStoreImpl.getInstance());
        }
        return instance;
    }
}
