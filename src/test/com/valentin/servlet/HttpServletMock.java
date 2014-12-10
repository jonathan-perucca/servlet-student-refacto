package com.valentin.servlet;

import com.valentin.service.UserService;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletMock {

    @Mock
    ServletContext servletContext;

    @Mock
    ServletConfig servletConfig;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UserService userService;

    @Mock
    RequestDispatcher requestDispatcher;
}
