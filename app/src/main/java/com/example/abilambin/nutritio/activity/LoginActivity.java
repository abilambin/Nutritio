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
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.example.abilambin.nutritio.utils.PersonSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.email_login) TextView email;
    @BindView(R.id.password_login) TextView password;

    public static String APP_INFO_NAME = "NutritioGlobalInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Vérification des SharedPreferences. i.e : Déjà connecté
        SharedPreferences prefs = this.getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        String authToken = prefs.getString("authToken", null);
        String username = prefs.getString("username", null);
        String userId = prefs.getString("id", null);
        String userInfo = prefs.getString("userInfo", null);

        if (authToken != null && username != null && userId != null && userInfo != null) {
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
        // Test la présence des informations
        if(email.getText() == ""){
            Toast.makeText(LoginActivity.this, R.string.forgot_email, Toast.LENGTH_SHORT).show();
        }else if(password.getText() == ""){
            Toast.makeText(LoginActivity.this, R.string.forgot_password, Toast.LENGTH_SHORT).show();
        }

        // Ajoute ces informations au singleton AuthenticateUser
        AuthenticateUser.getInstance().setAuthenticateInfo(email.getText().toString(), password.getText().toString());

        String authToken = "";
        try {
            // Test la cohérence des informations
            authToken = AuthenticateUser.getInstance().testAuthenticateInfo();


            // Si celles si sont correctes
            if(authToken != null){
                // Récupération des infos utilisateur
                Request getInfoUserRequest = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/account")
                        .header("Authorization", authToken)
                        .build();

                BackgroundRestCaller caller = new BackgroundRestCaller();
                caller.execute(getInfoUserRequest);
                JsonObject jsonUserInfo = new JsonParser().parse(caller.get()).getAsJsonObject();


                // Stockage dans les SharedPreferences
                SharedPreferences.Editor editor = getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE).edit();
                editor.putString("authToken", authToken);
                editor.putString("username", email.getText().toString());
                editor.putString("id", jsonUserInfo.get("id").getAsString());
                editor.putString("userInfo", new Gson().toJson(jsonUserInfo));
                //ON AJOUTE L'ID DE LA LISTE DE COURSE ET DU STOCK
                editor.putInt("listeId",2);
                editor.apply();

                // Go to DashBoard
                startActivity(new Intent(LoginActivity.this, DashBoard.class));
            }else{
                Toast.makeText(LoginActivity.this, R.string.unknown_user, Toast.LENGTH_SHORT).show();
            }
        } catch (CannotAuthenticateUserException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }
}
