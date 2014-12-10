package com.valentin.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupprimeServletTest extends HttpServletMock{

    @InjectMocks
    private SupprimeServlet supprimeServlet;

    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");

        supprimeServlet.doGet(request, response);

        verify(userService).removeUser(eq(1L));
        verify(response).sendRedirect("/ListPersonne");
    }
}