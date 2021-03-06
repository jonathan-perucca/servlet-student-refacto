package com.valentin.dao.impl;

import com.google.common.collect.Lists;
import com.valentin.domain.User;
import com.valentin.store.impl.ListStoreImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoImplTest {

    UserDaoImpl userDao;

    User user;

    Long id;
    String firstName;
    String genre;
    String birthday;

    @Before
    public void setUp() {
        id = 1L;
        firstName = "John";
        genre = "Smith";
        birthday = "2010-01-01";
        user = new User.Builder().withId(id).withNom(firstName).withGenre(genre).withBirthday(birthday).build();
        userDao = new UserDaoImpl(new ListStoreImpl(Lists.newArrayList()));
    }

    @After
    public void tearDown() {
        userDao = null;
    }

    @Test
    public void shouldSize_With_NoData() {
        assertThat(userDao.size(), is(0));
    }

    @Test
    public void shouldGet() {
        userDao.save(user);

        User retrievedUser = userDao.get(Long.valueOf(1));

        assertThatUserIsNotNullAndMapped(retrievedUser);
        assertEquality(retrievedUser);
    }

    @Test
    public void shouldSave() {
        userDao.save(user);

        assertThat(userDao.size(), is(1));
    }

    @Test
    public void shouldUpdate() {
        userDao.save(user);

        String newFirstName = "Lord";
        user.setNom(newFirstName);

        userDao.save(user);

        User userUpdated = userDao.get(Long.valueOf(1));

        assertThat(userUpdated, notNullValue());
        assertThat(userUpdated.getNom(), is(newFirstName));
    }

    @Test
    public void shouldDeleteUser() {
        userDao.save(user);

        boolean removed = false;

        assertThat(removed, is(false));

        removed = userDao.remove(user.getId());

        assertThat(removed, is(true));
    }

    @Test
    public void shouldGetAllUsers() {
        userDao.save(user);
        User userTwo = new User.Builder().withId(2L)
                                         .withNom("Alan")
                                         .withGenre("Bred")
                                         .withBirthday("1895-05-21")
                                         .build();
        userDao.save(userTwo);

        List<User> users = userDao.getUsers();

        assertThat(users.size(), is(2));
        assertThat(users, hasItem(user));
    }

    @Test
    public void shouldGetSingletonInstance() {
        UserDaoImpl userDao1 = userDao.getInstance();
        UserDaoImpl userDao2 = userDao.getInstance();

        assertThat(userDao1, is(userDao2));
    }

    private void assertThatUserIsNotNullAndMapped(User retrievedUser) {
        assertThat(retrievedUser, notNullValue());
        assertThat(retrievedUser.getId(), is(id));
        assertThat(retrievedUser.getNom(), is(firstName));
        assertThat(retrievedUser.getGenre(), is(genre));
        assertThat(retrievedUser.getBirthday(), is(birthday));
    }

    private void assertEquality(User retrievedUser) {
        User userToTest = new User.Builder().build();
        userToTest.setId(user.getId());

        assertThat(retrievedUser.equals(userToTest), is(true));
        assertThat(retrievedUser.hashCode(), is(userToTest.hashCode()));
    }
}