package com.valentin.servlet;

import com.valentin.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonneServletTest extends HttpServletMock<PersonneServlet> {

    @InjectMocks
    private PersonneServlet personneServlet;

    @Mock
    private List userList;

    @Mock
    private User user;

    @Test
    public void testDoGet() throws Exception {
        when(userService.getUsers()).thenReturn(userList);

        personneServlet.doGet(request, response);

        verify(servletContext).getRequestDispatcher(eq("/ListPersonne.jsp"));
    }

    @Test
    public void testDoPost_With_ValidUser() throws Exception {
        prepareNotValidUserForm();
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        personneServlet.doPost(request, response);

        verify(request).setAttribute(eq("message"), messageCaptor.capture());
        assertThat(messageCaptor.getValue(), is("Erreur - Vous n'avez pas rempli tous les champs obligatoires."));
        verify(servletContext).getRequestDispatcher(eq("/CreatePerson.jsp"));
    }

    private void prepareNotValidUserForm() throws Exception  {
        when(request.getParameter(anyString())).thenReturn("");
    }

    @Test
    public void testDoPost_With_NotValidUser() throws Exception {
        prepareUserForm();
        when(userService.saveUser(anyString(), anyString(), anyString())).thenReturn(user);

        personneServlet.doPost(request, response);

        verify(response).sendRedirect("/ListPersonne");
    }

    private void prepareUserForm() {
        when(request.getParameter(anyString())).thenReturn("test");
        when(request.getParameter("id")).thenReturn("1");
    }
}