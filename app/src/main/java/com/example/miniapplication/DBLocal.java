package com.example.miniapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.miniapplication.ui.Produit;
import com.example.miniapplication.ui.User;

import java.util.ArrayList;
import java.util.List;

public class DBLocal extends SQLiteOpenHelper {


    public DBLocal(Context context) {
        super(context, "db_local.db", null, 2);//creation de la base de donnée
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR(50), password VARCHAR(50), Nom VARCHAR(50),Prenom varchar(50), Adresse VARCHAR(50), Telephone VARCHAR(50) );");
        db.execSQL("CREATE TABLE produits(id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR (50), nomproduit VARCHAR (50), Prix VARCHAR(50), Lieuachat VARCHAR(50) );");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");//delete de la table user
        db.execSQL("DROP TABLE IF EXISTS produits");//delete de la table user
        //onCreate(db);// recreation de la table
    }


    public boolean addUser(String login, String password, String Nom, String Prenom, String Adresse, String Telephone) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("password", password);
            cv.put("Nom", Nom);
            cv.put("Prenom", Prenom);
            cv.put("Adresse", Adresse);
            cv.put("Telephone", Telephone);


            db.insert("user", null, cv);
            //db.insert("user", cv, "login='" + login + "'", null );

            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUser(String oldLogin, String login, String password, String Nom, String Prenom, String Adresse, String Telephone) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("password", password);
            cv.put("Nom", Nom);
            cv.put("Prenom", Prenom);
            cv.put("Adresse", Adresse);
            cv.put("Telephone", Telephone);

            db.update("user", cv, "login='" + oldLogin + "'", null);

            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public User getUser(String login, String password) {
        User user = new User();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("user", null, null, null, null, null, null);
            if (c.getCount() > 0) {


                c.moveToFirst();

                user.setAdresse(c.getString(c.getColumnIndex("Adresse")));
                user.setLogin(c.getString(c.getColumnIndex("login")));
                user.setLogin(c.getString(c.getColumnIndex("login")));
                user.setPassword(c.getString(c.getColumnIndex("password")));
                user.setNom(c.getString(c.getColumnIndex("Nom")));
                user.setPrenom(c.getString(c.getColumnIndex("Prenom")));
                user.setTelephone(c.getString(c.getColumnIndex("Telephone")));
                db.close();

                if(user.getLogin().equals(login) && user.getPassword().equals(password))
                {
                    return user;
                }
                else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public boolean addProduit(String date, String nomproduit, String Prix, String Lieuachat) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("date", date);
            cv.put("nomproduit", nomproduit);
            cv.put("Prix", Prix);
            cv.put("Lieuachat", Lieuachat);

            db.insert("produits", null, cv);

            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduits() {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            db.delete("produits", "1=1", null);

            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  List<Produit>   getProduits() {
        List<Produit> list = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("produits", null, null, null, null, null, null);
            if (c.getCount() > 0) {

                c.moveToFirst();
                do{
                    Produit produit = new Produit();
                    produit.setDate(c.getString(c.getColumnIndex("date")));
                    produit.setLieu(c.getString(c.getColumnIndex("Lieuachat")));
                    produit.setNom(c.getString(c.getColumnIndex("nomproduit")));
                    produit.setPrix(c.getString(c.getColumnIndex("Prix")));
                    c.moveToNext();

                    list.add(produit);

                }while(!c.isAfterLast());

            } else {
                return list;
            }

            db.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }
}


