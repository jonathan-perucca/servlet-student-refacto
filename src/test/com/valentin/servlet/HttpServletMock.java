package com.valentin.servlet;

import com.valentin.service.UserService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class HttpServletMock<T extends HttpServlet> {

    @Mock
    ServletContext servletContext;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UserService userService;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    T servlet;

    @Before
    public void setUp() {
        initServletBehavior();
    }

    public void initServletBehavior() {
        Mockito.mock(ServletConfig.class);
        // Servlet dependent
        when(servlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);
    }
}
