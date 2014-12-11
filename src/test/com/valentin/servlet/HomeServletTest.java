package com.valentin.servlet;

import com.google.common.collect.Lists;
import com.valentin.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeServletTest extends HttpServletMock<HomeServlet> {

    @InjectMocks
    private HomeServlet homeServlet;

    @Test
    public void testDoGet() throws ServletException, IOException {
        List<User> users = Lists.newArrayList();
        users.add(new User(1L, "john", "male", "2010-01-01"));

        // Functional test code
        when(userService.getUsers()).thenReturn(users);

        homeServlet.doGet(request, response);

        verify(request).setAttribute(eq("listUser"), eq(users)); // only if you want to verify username was called...
    }
}