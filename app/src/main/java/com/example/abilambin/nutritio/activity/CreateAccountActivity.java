package com.example.abilambin.nutritio.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.CannotCreateUserException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.example.abilambin.nutritio.utils.RegisterUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.new_email) TextView email;
    @BindView(R.id.new_firstname) TextView firstname;
    @BindView(R.id.new_lastname) TextView lastname;
    @BindView(R.id.new_password) TextView password;
    @BindView(R.id.new_password_confirmation) TextView passwordConfirmation;
    @BindView(R.id.new_birthdate) TextView birthdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        findViewById(R.id.submit_account).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(this.validateField()) {
            RegisterUser registerUser = new RegisterUser(
                    CreateAccountActivity.this,
                    this,
                    email.getText().toString(),
                    firstname.getText().toString(),
                    lastname.getText().toString(),
                    password.getText().toString());

            registerUser.execute();
        }
    }

    /**
     * Vérifie que la cohérence des champs
     * @return True si ceux-ci sont correctement renseignés, false sinon
     */
    private boolean validateField(){
        if(email.getText().toString().trim().equals("") || !email.getText().toString().contains("@")) {
            Toast.makeText(CreateAccountActivity.this, R.string.must_have_email, Toast.LENGTH_SHORT).show();
            return false;
        }else if(firstname.getText().toString().trim().equals("")){
            Toast.makeText(CreateAccountActivity.this, R.string.must_have_firstname, Toast.LENGTH_SHORT).show();
            return false;
        }else if(lastname.getText().toString().trim().equals("")){
            Toast.makeText(CreateAccountActivity.this, R.string.must_have_lastname, Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.getText().toString().equals("")){
            Toast.makeText(CreateAccountActivity.this, R.string.must_have_password, Toast.LENGTH_SHORT).show();
            return false;
        }else if(!passwordConfirmation.getText().toString().equals(password.getText().toString())){
            Toast.makeText(CreateAccountActivity.this, R.string.bad_password_confirmation, Toast.LENGTH_SHORT).show();
            return false;
        }else if(birthdate.getText().toString().equals("")){
            Toast.makeText(CreateAccountActivity.this, R.string.must_have_birthdate, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
