package com.valentin.servlet;

import com.valentin.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateServletTest extends HttpServletMock<UpdateServlet>{

    @InjectMocks
    private UpdateServlet updateServlet;

    @Mock
    private User user;

    @Test
    public void testDoGet() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(user);
        when(request.getParameter("id")).thenReturn("1");

        updateServlet.doGet(request, response);

        verify(request).setAttribute(eq("user"), eq(user));
        verify(servletContext).getRequestDispatcher("/UpdatePersonne.jsp");
    }

    @Test
    public void testDoPost_With_ValidUser() throws Exception {
        prepareNotValidUserForm();
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        updateServlet.doPost(request, response);

        verify(request).setAttribute(eq("message"), messageCaptor.capture());
        assertThat(messageCaptor.getValue(), is("Erreur - Vous n'avez pas rempli tous les champs obligatoires. Veuillez Recommencer"));
    }

    private void prepareNotValidUserForm() throws Exception  {
        when(request.getParameter(anyString())).thenReturn("");
    }

    @Test
    public void testDoPost_With_NotValidUser() throws Exception {
        prepareUserForm();
        when(userService.getUser(anyLong())).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);

        updateServlet.doPost(request, response);

        verify(request).setAttribute(eq("user"), eq(this.user));
    }

    private void prepareUserForm() {
        when(request.getParameter(anyString())).thenReturn("test");
        when(request.getParameter("id")).thenReturn("1");
    }
}