package com.naldonatanael.project_uts.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest9941 {
    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);
    }
    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
        when(view.getEmail()).thenReturn("");
        System.out.println("email : "+view.getEmail());
        presenter.onLoginClicked();
        verify(view).showEmailError("Email Tidak Boleh Kosong");
    }
    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(view.getEmail()).thenReturn("admin");
        System.out.println("email : "+ view.getEmail());
        when(view.getPassword()).thenReturn("");
        System.out.println("password : "+view.getPassword());
        presenter.onLoginClicked();
        verify(view).showPasswordError("Password Tidak Boleh Kosong");
    }
    @Test
    public void shouldStartMainActivityWhenEmailAndPasswordAreCorrect() throws
            Exception {
        when(view.getEmail()).thenReturn("admin");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("admin");
        System.out.println("password : "+view.getPassword());
        when(service.getValid(view, view.getEmail(),
                view.getPassword())).thenReturn(true);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(),
                view.getPassword()));
        presenter.onLoginClicked();
    }
    @Test
    public void shouldShowLoginErrorWhenEmailAndPasswordAreInvalid() throws
            Exception {
        when(view.getEmail()).thenReturn("admin");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("admins");
        System.out.println("password : "+view.getPassword());
        when(service.getValid(view, view.getEmail(),
                view.getPassword())).thenReturn(false);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(),
                view.getPassword()));
        presenter.onLoginClicked();

    }
}