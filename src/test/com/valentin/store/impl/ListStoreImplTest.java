package com.valentin.store.impl;

import com.google.common.collect.Lists;
import com.valentin.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ListStoreImplTest {

    private ListStoreImpl<User, Long> listStore;
    private User user;

    @Before
    public void setUp() {
        listStore = new ListStoreImpl(Lists.newArrayList());

        user = new User.Builder().withNom("John").withBirthday("2010-01-01").build();
    }

    @Test
    public void testGet() {
        User save = listStore.save(user);

        User userAsSame = listStore.get(save.getId());

        assertThat(userAsSame, is(save));
    }
    
    @Test
    public void testGetNullUser() {
        User nullUser = listStore.get(3L);

        assertThat(nullUser, nullValue());
    }

    @Test
    public void testSave() {
        assertThat(listStore.count(), is(0));

        User save = listStore.save(user);

        assertThat(listStore.count(), is(1));
        assertThat(save.getId(), notNullValue());
    }

    @Test
    public void testDelete() {
        User save = listStore.save(user);

        assertThat(listStore.count(), is(1));

        boolean deleted = listStore.delete(save.getId());

        assertThat(deleted, is(true));
        assertThat(listStore.count(), is(0));
    }

    @Test
    public void testDeleteNoOne() {
        boolean deleted = listStore.delete(3L);

        assertThat(deleted, is(false));
    }

    @Test
    public void testCount() {
        User save = listStore.save(user);

        assertThat(listStore.count(), is(1));
        assertThat(save.getId(), is(1L));
    }

    @Test
    public void testGetAll() {
        User saveOne = listStore.save(user);
        user.setId(null);
        User saveTwo = listStore.save(user);

        List<User> listUser = listStore.getAll();

        assertThat(listUser, hasItems(saveOne, saveTwo));
    }

    @Test
    public void testGetInstance() {
        ListStoreImpl instanceOne = listStore.getInstance();
        ListStoreImpl instanceTwo = listStore.getInstance();

        assertThat(instanceOne, is(instanceTwo));
    }
}