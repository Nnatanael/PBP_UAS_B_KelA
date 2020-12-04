package com.naldonatanael.project_uts.UnitTest;

import com.naldonatanael.project_uts.dao.UserDAO;

public interface LoginCallback {
    void onSuccess(boolean value, UserDAO user);
    void onError();
}
