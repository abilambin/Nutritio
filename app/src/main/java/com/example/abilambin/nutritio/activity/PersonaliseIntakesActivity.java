package com.example.abilambin.nutritio.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.utils.PersonalGoal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonaliseIntakesActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.protein_intake)
    EditText protein_intake;

    @BindView(R.id.glucide_intake)
    EditText glucide_intake;

    @BindView(R.id.lipide_intake)
    EditText lipide_intake;

    @BindView(R.id.sucre_intake)
    EditText sucre_intake;

    @BindView(R.id.fibre_intake)
    EditText fibre_intake;

    @BindView(R.id.ags_intake)
    EditText ags_intake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalise_intakes);

        ButterKnife.bind(this);

        findViewById(R.id.valid_intake).setOnClickListener(this);

        protein_intake.setText(PersonalGoal.getInstance().getProteineNeeds() + "");
        glucide_intake.setText(PersonalGoal.getInstance().getGlucideNeeds() + "");
        lipide_intake.setText(PersonalGoal.getInstance().getLipideNeeds() + "");
        sucre_intake.setText(PersonalGoal.getInstance().getSucreNeeds() + "");
        fibre_intake.setText(PersonalGoal.getInstance().getFibreNeeds() + "");
        ags_intake.setText(PersonalGoal.getInstance().getAgsNeeds() + "");
    }

    @Override
    public void onClick(View view) {
        String proteineNeeds = protein_intake.getText().toString();
        String glucideNeeds = glucide_intake.getText().toString();
        String lipideNeeds = lipide_intake.getText().toString();
        String sucreNeeds = sucre_intake.getText().toString();
        String fibreNeeds = fibre_intake.getText().toString();
        String agsNeeds = ags_intake.getText().toString();

        PersonalGoal.getInstance().setProteineNeeds(Math.round(Float.parseFloat(proteineNeeds)));
        PersonalGoal.getInstance().setGlucideNeeds(Math.round(Float.parseFloat(glucideNeeds)));
        PersonalGoal.getInstance().setLipideNeeds(Math.round(Float.parseFloat(lipideNeeds)));
        PersonalGoal.getInstance().setSucreNeeds(Math.round(Float.parseFloat(sucreNeeds)));
        PersonalGoal.getInstance().setFibreNeeds(Math.round(Float.parseFloat(fibreNeeds)));
        PersonalGoal.getInstance().setAgsNeeds(Math.round(Float.parseFloat(agsNeeds)));

        Toast.makeText(this, "Objectifs mis à jour", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
