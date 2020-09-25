package com.example.miniapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniapplication.ui.Produit;
import com.example.miniapplication.ui.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin, txtPassword;
    private Button btnConnect;
    private String login, password;

    private DBLocal dbLocal ;
    public static User user ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);

        btnConnect = findViewById(R.id.btnConnect);
        dbLocal = new DBLocal(this);

        List<Produit> list = dbLocal.getProduits();
        dbLocal.deleteProduits();

        //if(list.size()==0){


            dbLocal.addUser("test", "passer", "test", "test", "test", "770000000");
            dbLocal.addProduit("24/09/2020", "Lait", "200 FCFA", "EDK");
            dbLocal.addProduit("23/09/2020", "Savon", "500 FCFA", "Sandaga");
            dbLocal.addProduit("21/09/2020", "Sucre", "1000 FCFA", "Auchan");
            dbLocal.addProduit("20/09/2020", "Mangue", "250 FCFA", "EDK");
            dbLocal.addProduit("12/09/2020", "Huile", "1000 FCFA", "Kaolack");
            dbLocal.addProduit("12/09/2020", "Huile", "1000 FCFA", "Kaolack");
            dbLocal.addProduit("12/09/2020", "Huile", "1000 FCFA", "Kaolack");
       // }


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLogin.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                if(login.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Veuillez renseigner les Champs", Toast.LENGTH_SHORT).show();
                }
                else{

                   user= dbLocal.getUser(login, password);

                    if(user==null)
                    {
                        Toast.makeText(MainActivity.this, "Vos paramétres sont incorrects", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    }
            }
        });
    }
}
