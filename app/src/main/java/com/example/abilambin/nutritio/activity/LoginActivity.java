package com.example.abilambin.nutritio.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.email_login) TextView email;
    @BindView(R.id.password_login) TextView password;

    public static String APP_INFO_NAME = "NutritioGlobalInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Vérification des SharedPreferences. i.e : Déjà connecté
        SharedPreferences prefs = getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        String authToken = prefs.getString("authToken", null);
        String username = prefs.getString("username", null);

        if (authToken != null && username != null) {
            AuthenticateUser.getInstance().setAuthenticateToken(authToken);
            startActivity(new Intent(LoginActivity.this, DashBoard.class));
        }

        ButterKnife.bind(this);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.create_account_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(email.getText() == ""){
            Toast.makeText(LoginActivity.this, R.string.forgot_email, Toast.LENGTH_SHORT).show();
        }else if(password.getText() == ""){
            Toast.makeText(LoginActivity.this, R.string.forgot_password, Toast.LENGTH_SHORT).show();
        }

        AuthenticateUser.getInstance().setAuthenticateInfo(email.getText().toString(), password.getText().toString());

        String authToken = "";
        try {
            authToken = AuthenticateUser.getInstance().testAuthenticateInfo();

            if(authToken != null){
                SharedPreferences.Editor editor = getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE).edit();
                editor.putString("authToken", authToken);
                editor.putString("username", email.getText().toString());
                editor.apply();

                startActivity(new Intent(LoginActivity.this, DashBoard.class));
            }else{
                Toast.makeText(LoginActivity.this, R.string.unknown_user, Toast.LENGTH_SHORT).show();
            }
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }
}
