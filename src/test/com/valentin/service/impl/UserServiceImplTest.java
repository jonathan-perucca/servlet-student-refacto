package com.valentin.service.impl;

import com.google.common.collect.Lists;
import com.valentin.dao.impl.UserDaoImpl;
import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.store.impl.ListStoreImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserServiceImplTest {

    UserService userService;
    private String firstName;
    private String birthdate;
    private String genre;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(new UserDaoImpl(new ListStoreImpl(Lists.newArrayList())));
        firstName = "John";
        genre = "Smith";
        birthdate = "1901-01-01";
    }

    @After
    public void tearDown() {
        userService = null;
    }

    @Test
    public void shouldSaveUser() {
        User user = insertUser();

        assertThat(user.getId(), notNullValue());
        assertThat(user.getId(), is(1L));
    }

    @Test
    public void shouldGetUser() {
        insertUser();

        User user = userService.getUser(1L);

        assertThat(user.getNom(), is(firstName));
        assertThat(user.getGenre(), is(genre));
        assertThat(user.getBirthday(), is(birthdate));
    }

    @Test
    public void shouldGetAllUsers() {
        User user1 = insertUser();
        User user2 = insertUser();

        List<User> users = userService.getUsers();

        assertThat(users, hasItems(user1, user2));
    }

    @Test
    public void shouldDeleteUser() {
        User user = insertUser();

        boolean deleted = userService.removeUser(user.getId());

        assertThat(deleted, is(true));
    }

    @Test
    public void shouldGetSingletonInstance() {
        UserService userService1 = UserServiceImpl.getInstance();
        UserService userService2 = UserServiceImpl.getInstance();

        assertThat(userService1, is(userService2));
    }

    private User insertUser() {
        return userService.saveUser(firstName, genre, birthdate);
    }
}