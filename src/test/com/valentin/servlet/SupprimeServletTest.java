package com.valentin.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupprimeServletTest extends HttpServletMock<SupprimeServlet>{

    @InjectMocks
    private SupprimeServlet supprimeServlet;

    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");

        supprimeServlet.doGet(request, response);

        verify(userService).removeUser(eq(1L));
        verify(response).sendRedirect("/ListPersonne");
    }

    @Test
    public void testDoGet_Message_Unregistered() throws ServletException, IOException {
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        when(request.getParameter("id")).thenReturn("1");

        supprimeServlet.doGet(request, response);

        verify(request).setAttribute(eq("message"), messageCaptor.capture());
        assertThat(messageCaptor.getValue(), is("Personne non enregistrée"));
    }

    @Test
    public void testDoGet_Message_Registered() throws ServletException, IOException {
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        when(request.getParameter("id")).thenReturn("1");
        when(userService.removeUser(anyLong())).thenReturn(true);

        supprimeServlet.doGet(request, response);

        verify(request).setAttribute(eq("message"), messageCaptor.capture());
        assertThat(messageCaptor.getValue(), is("Personne Correctement supprimé"));
    }
}