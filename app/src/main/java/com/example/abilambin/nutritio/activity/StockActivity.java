package com.example.abilambin.nutritio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends AbstractNavigationActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_stock;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.stocks;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }





    package com.example.abilambin.gestionparc;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.support.v7.widget.RecyclerView;
import com.example.abilambin.gestionparc.adapter.ProblemAdapter;
import com.example.abilambin.gestionparc.model.Problem;

import java.util.ArrayList;
import java.util.List;


    //TODO :
/*
- base de donnée

- Faire action sur le bouton ajouter

- Input int (clavier chiffres ?) pour lat et long

- Affichage carte et localisation

 */
    public class MainActivity extends AppCompatActivity {

        List<Problem> problems;
        RecyclerView recyclerView;

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            FloatingActionButton fab = this.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddProblemActivity.class);
                    //démarrage de l'intent
                    startActivity(intent);
                }
            });

            Button button = findViewById(R.id.init);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    init();
                }
            });

        }

        @Override
        protected void onResume() {
            super.onResume();
            this.updateView();

        }

        protected void init() {
            Problem.deleteAll(Problem.class);

            Problem problem1 = new Problem("Arbre à tailler", 48.866667,  2.333333, "Paris", "L'arbre est vert.");
            Problem problem2 = new Problem("Mauvaise herbe", 51.507351, -0.127758, "Londres", "C'est ici.");
            Problem problem3 = new Problem("Autre",35.759465,-5.833954,"Tanger, Maroc","Trous dans le trottoir");
            Problem problem4 = new Problem("Haie à tailler",50.62925,3.057256,"Lille","");
            Problem problem5 = new Problem("Autre",52.520007,13.404954,"Berlin, Allemagne","");
            Problem problem6 = new Problem("Arbre à abattre",34,50.4,"","");
            Problem problem7 = new Problem("Détritus",53.349805,-6.26031,"Dublin, Irlande","");
            Problem problem8 = new Problem("Arbre à abattre",2,12.3,"","");
            Problem problem9 = new Problem("Mauvaise herbe",7,50.4,"","");
            Problem problem10 = new Problem("Détritus",2,12.3,"","");

            problem1.save();
            problem2.save();
            problem3.save();
            problem4.save();
            problem5.save();
            problem6.save();
            problem7.save();
            problem8.save();
            problem9.save();
            problem10.save();

            this.updateView();
            Toast.makeText(this, "Base de données initialisée", Toast.LENGTH_SHORT).show();

        }

        protected void updateView(){
            this.problems = new ArrayList<>();
            this.problems = Problem.listAll(Problem.class);

            try {
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                ProblemAdapter problemAdapter = new ProblemAdapter(problems);
                recyclerView.setAdapter(problemAdapter);
                problemAdapter.notifyDataSetChanged();
                itemTouchGestion(recyclerView);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "no data loading", Toast.LENGTH_SHORT).show();
            }

        }


        protected void itemTouchGestion(final RecyclerView recyclerView) {
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);

                            Stock problem = ((StockAdapter)recyclerView.getAdapter()).getItem(position);

                            //ajout des informations au bundle à envoyer
                            Bundle bundle = new Bundle();
                            bundle.putLong("problemId", problem.getId());
                            bundle.putString("type", problem.getType());
                            bundle.putDouble("lat", problem.getLatitude());
                            bundle.putDouble("long", problem.getLongitude());
                            bundle.putString("address", problem.getAddress());
                            bundle.putString("precisions", problem.getPrecisions());

                            //ajout du bundle à l'intent
                            intent.putExtras(bundle);
                            //démarrage de l'intent
                            startActivity(intent);
                        }

                        @Override public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );
        }
    }

}
