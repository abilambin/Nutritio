package com.example.abilambin.nutritio.bdd.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Groceries;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.MealIngredient;
import com.example.abilambin.nutritio.bdd.model.Stock;
import com.example.abilambin.nutritio.bdd.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by serial on 04/12/2017.
 *
 * <p>Cette classe permet d'accéder à la base</p>
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /*
     * variables statiques pour le nom de la DB et son numéro de version
     */

    /**
     * nom de la DB
     */
    private static final String DATABASE_NAME = "nutritio.db";

    /**
     * version de la DB
     */
    private static final int DATABASE_VERSION = 1;


    /**
     * Dao pour les Users
     */
    private Dao<User, Integer> userDao;

    private Dao<Ingredient, Integer> ingredientsDao;

    private Dao<Meal, Integer> mealsDao;

    private Dao<Stock, Integer> stocksDao;

    private Dao<Groceries, Integer> groceriesDao;

    private Dao<MealIngredient, Integer> mealIngredientsDao;

    /**
     * Constructeur de l'objet DatabaseHelper
     * @param context le contexte de l'application
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            // création des tables
            TableUtils.createTable(connectionSource, Ingredient.class);
            TableUtils.createTable(connectionSource, Meal.class);
            TableUtils.createTable(connectionSource, Groceries.class);
            //TableUtils.createTable(connectionSource, Stock.class);
            TableUtils.createTable(connectionSource, MealIngredient.class);
            //TableUtils.createTable(connectionSource, User.class);

            //insertion de mocks
            //TODO supprimer les insertions quand plus besoin
            Date date = new Date(2018,02,20);
            date.setHours(8);
            Meal meal = new Meal("Saumon aux brocolis", 10, 10);
            meal.setDate(date);
            this.getMealsDao().create(meal);

            date.setHours(12);
            meal = new Meal("Soupe de potiron", 30, 20);
            meal.setDate(date);
            this.getMealsDao().create(meal);

            date.setHours(19);
            meal = new Meal("Carbonnade Flammande", 30, 20);
            meal.setDate(date);
            this.getMealsDao().create(meal);

            this.getMealsDao().create(new Meal("Soupe de poireaux", 30, 20));
            this.getMealsDao().create(new Meal("Petit panaché", 30, 20));
            this.getMealsDao().create(new Meal("Croquettes de poisson", 30, 20));
            this.getMealsDao().create(new Meal("Epinards à la crème", 30, 20));
            this.getMealsDao().create(new Meal("Wrap de nuggets", 30, 20));
            this.getMealsDao().create(new Meal("Cuillère d'huile de foie de morue", 30, 20));
            this.getMealsDao().create(new Meal("Haricots verts", 30, 20));
            this.getMealsDao().create(new Meal("Purée Mousline", 30, 20));

            this.getIngredientsDao().create(createIngredient("Pomme", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Pomme", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Potiron", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Riz", "Féculents"));
            this.getIngredientsDao().create(createIngredient("Banane", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Poulet", "Viande"));
            this.getIngredientsDao().create(createIngredient("Orange", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Pomme de terre", "Fruits et Légumes"));
            this.getIngredientsDao().create(createIngredient("Boeuf", "Viande"));
            this.getIngredientsDao().create(createIngredient("Camenbert", "Fromage"));
            this.getIngredientsDao().create(createIngredient("Vin", "Alcool"));


        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create tables", e);
            System.out.println("##### ERROR - Impossible de créer les tables");
        }
    }

    private Ingredient createIngredient(String name, String categorie){
        Ingredient i = new Ingredient();
        i.setName(name);
        i.setBrand(categorie);
        return i;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            // Dans le cas d'un éventuel changement dans la base pour une nouvelle version de l'appli
            // incrémentez la varaible DATABASE_VERSION, alors cette méthode sera invoquée
            // automatiquement.

            // Le développeur doit gérer la logique de mise à niveau ici, c'est-à-dire créer
            // une nouvelle table ou une nouvelle colonne dans une table existante,
            // prendre les sauvegardes de la base de données existante, etc.

            TableUtils.dropTable(connectionSource, MealIngredient.class, true);
            TableUtils.dropTable(connectionSource, Ingredient.class, true);
            TableUtils.dropTable(connectionSource, Meal.class, true);
            TableUtils.dropTable(connectionSource, Groceries.class, true);
            TableUtils.dropTable(connectionSource, Stock.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version "
                    + oldVersion + " to new " + newVersion, e);
        }
    }

    /**
     * permet de récupérer le Dao des Users
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    /**
     * permet de récupérer le Dao des Ingredients
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<Ingredient, Integer> getIngredientsDao() throws SQLException {
        if (ingredientsDao == null) {
            ingredientsDao = getDao(Ingredient.class);
        }
        return ingredientsDao;
    }

    /**
     * permet de récupérer le Dao des Meals
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<Meal, Integer> getMealsDao() throws SQLException {
        if (mealsDao == null) {
            mealsDao = getDao(Meal.class);
        }
        return mealsDao;
    }

    /**
     * permet de récupérer le Dao des Groceries
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<Groceries, Integer> getGroceriesDao() throws SQLException {
        if (groceriesDao == null) {
            groceriesDao = getDao(Groceries.class);
        }
        return groceriesDao;
    }

    /**
     * permet de récupérer le Dao des Stock
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<Stock, Integer> getStocksDao() throws SQLException {
        if (stocksDao == null) {
            stocksDao = getDao(Stock.class);
        }
        return stocksDao;
    }

    /**
     * permet de récupérer le Dao des MealIngredient
     * @return Dao
     * @throws SQLException Exception SQL
     */
    public Dao<MealIngredient, Integer> getMealIngredientDao() throws SQLException {
        if(mealIngredientsDao == null){
            mealIngredientsDao = getDao(MealIngredient.class);
        }
        return mealIngredientsDao;
    }
}
