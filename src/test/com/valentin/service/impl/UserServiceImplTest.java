package com.valentin.service.impl;

import com.google.common.collect.Lists;
import com.valentin.dao.impl.UserDaoImpl;
import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.store.impl.ListStoreImpl;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
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
        User user = inserUser();

        assertThat(user.getId(), notNullValue());
        assertThat(user.getId(), is(1L));
    }

    @Test
    public void shouldGetUser() {
        inserUser();

        User user = userService.getUser(1L);

        assertThat(user.getNom(), is(firstName));
        assertThat(user.getGenre(), is(genre));
        assertThat(user.getBirthday(), is(birthdate));
    }

    @Test
    public void shouldDeleteUser() {
        User user = inserUser();

        boolean deleted = userService.removeUser(user.getId());

        assertThat(deleted, is(true));
    }

    @Test
    public void testNewInstance() {
        UserService userService1 = UserServiceImpl.newInstance();
        UserService userService2 = UserServiceImpl.newInstance();

        assertThat(userService1, not(userService2));
    }

    private User inserUser() {
        return userService.saveUser(firstName, genre, birthdate);
    }
}