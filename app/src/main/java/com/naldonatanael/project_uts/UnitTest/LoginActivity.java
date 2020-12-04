package com.naldonatanael.project_uts.UnitTest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naldonatanael.project_uts.R;
import com.naldonatanael.project_uts.RegisterActivity;
import com.naldonatanael.project_uts.FragmentRegister;
import com.naldonatanael.project_uts.dao.UserDAO;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private Button btnLogin;
    private TextInputEditText email, password;
    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnSignIn);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        presenter = new LoginPresenter(this, new LoginService());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLoginClicked();
            }
        });
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }
    @Override
    public void showEmailError(String message) {
        email.setError(message);
    }
    @Override
    public String getPassword() {
        return password.getText().toString();
    }
    @Override
    public void showPasswordError(String message) {
        password.setError(message);
    }
    @Override
    public void startMainActivity() {
        new ActivityUtil(this).startMainActivity();
    }
    @Override
    public void startUserProfileActivity(UserDAO user) {
        new ActivityUtil(this).startUserProfile(user);
    }
    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}