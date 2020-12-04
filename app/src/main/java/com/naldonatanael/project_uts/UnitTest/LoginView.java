package com.naldonatanael.project_uts.UnitTest;

import com.naldonatanael.project_uts.dao.UserDAO;

public interface LoginView {
    String getEmail();
    void showEmailError(String message);
    String getPassword();
    void showPasswordError(String message);
    void startMainActivity();
    void startUserProfileActivity(UserDAO user);
    void showLoginError(String message);
    void showErrorResponse(String message);
}
