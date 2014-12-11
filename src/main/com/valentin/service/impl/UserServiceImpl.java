package com.valentin.service.impl;

import com.valentin.dao.UserDao;
import com.valentin.dao.impl.UserDaoImpl;
import com.valentin.domain.User;
import com.valentin.service.UserService;

import java.util.List;

/**
 * Service handles business work on entities
 */
public class UserServiceImpl implements UserService{

	private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(Long id) {
        return userDao.get(id);
    }


    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
	public User saveUser(String nom, String genre, String birthDay){
        User user = new User.Builder().withNom(nom)
                                      .withGenre(genre)
                                      .withBirthday(birthDay)
                                      .build();
        return saveUser(user);
	}

    @Override
    public boolean removeUser(Long id) {
        return userDao.remove(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    private static UserServiceImpl instance;

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserServiceImpl(UserDaoImpl.getInstance());
        }
        return instance;
    }
}