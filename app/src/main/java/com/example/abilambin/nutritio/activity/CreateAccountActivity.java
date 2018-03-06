package com.example.abilambin.nutritio.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
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
            JsonObject account = new JsonObject();                                  // JsonObject matérialisant le nouveau compte
            SimpleDateFormat dayDF = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat hourDF = new SimpleDateFormat("hh:mm:ss");
            Date now = Calendar.getInstance().getTime();
            JsonArray authorities = new JsonArray();                                // Droit de l'utilisateur
            authorities.add("ROLE_USER");                                           //

            // Remplissage des informations du compte
            try {
                account.addProperty("activated", true);
                account.add("authorities", authorities);
                account.addProperty("vreateBy", "Nutritio");
                account.addProperty("createdDate", dayDF.format(now) + "T" + hourDF.format(now) + "Z");      // Le '+ "Z" ' sert à être reconnut en tant que java.time.Instant par le serveur
                account.addProperty("email", email.getText().toString());
                account.addProperty("fistname", firstname.getText().toString());
                account.addProperty("lastname", lastname.getText().toString());
                account.addProperty("login", email.getText().toString());
                account.addProperty("password", password.getText().toString());
                account.addProperty("imageUrl", "");
                account.addProperty("langKey", "en");
                account.addProperty("lastModifiedBy", "Nutritio");
                account.addProperty("lastModifiedDate", dayDF.format(now) + "T" + hourDF.format(now) + "Z"); // Le '+ "Z" ' sert à être reconnut en tant que java.time.Instant par le serveur

                Request request = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/register")
                        .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(account)))
                        .build();

                BackgroundRestCaller caller = new BackgroundRestCaller();

                // Inscription
                caller.execute(request);

                // Récupération de la réponse sous fomre de JsonObject
                JsonObject response = new JsonParser().parse(caller.get()).getAsJsonObject();
                if (caller.getResponseCode() >= 300) {
                    Toast.makeText(CreateAccountActivity.this, "Error : " + response.get("title"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccountActivity.this, R.string.account_created, Toast.LENGTH_SHORT).show();
                }

                this.finish();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(CreateAccountActivity.this, R.string.error, Toast.LENGTH_SHORT);
            }
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
