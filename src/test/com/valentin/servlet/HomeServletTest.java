package com.valentin.servlet;

import com.google.common.collect.Lists;
import com.valentin.domain.User;
import com.valentin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeServletTest {

    @InjectMocks
    private HomeServlet homeServlet;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGet() throws Exception {
        List<User> users = Lists.newArrayList();
        users.add(new User(1L, "john", "male", "2010-01-01"));

        when(homeServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);
        when(userService.getUsers()).thenReturn(users);

        homeServlet.doGet(request, response);

        verify(request).setAttribute(eq("listUser"), eq(users)); // only if you want to verify username was called...
    }
}