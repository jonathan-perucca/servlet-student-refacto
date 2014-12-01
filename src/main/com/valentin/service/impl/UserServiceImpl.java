package com.valentin.service.impl;

import com.valentin.dao.UserDao;
import com.valentin.dao.impl.UserDaoImpl;
import com.valentin.domain.User;
import com.valentin.service.UserService;

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
	public User saveUser(String nom, String genre, String Birthday){
        User user = new User();
   		user.setNom(nom);
   		user.setGenre(genre);
   		user.setBirthday(Birthday);

        return userDao.save(user);
	}

    @Override
    public boolean removeUser(Long id) {
        return userDao.remove(id);
    }

    public static UserService newInstance() {
        return new UserServiceImpl(UserDaoImpl.newInstance());
    }
}