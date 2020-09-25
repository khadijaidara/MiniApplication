package com.example.miniapplication.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.miniapplication.DBLocal;
import com.example.miniapplication.HomeActivity;
import com.example.miniapplication.MainActivity;
import com.example.miniapplication.R;
import com.example.miniapplication.ui.User;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private Button btnEnregistrer;
    private String  nom, prenom, adresse, telephone, login, password;
    private EditText txtNom, txtPrenom, txtAdresse, txtTelephone, txtLoginProfil, txtPasswordProfil;
    private User user ;
    private DBLocal dbLocal;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        btnEnregistrer =  view.findViewById(R.id.btnEnregistrer);
        txtNom =  view.findViewById(R.id.txtNom);
        txtPrenom =  view.findViewById(R.id.txtPrenom);
        txtAdresse =  view.findViewById(R.id.txtAdresse);
        txtTelephone =  view.findViewById(R.id.txtTelephone);
        txtLoginProfil =  view.findViewById(R.id.txtLoginProfil);
        txtPasswordProfil =  view.findViewById(R.id.txtPasswordProfil);

        user = new User();
        dbLocal = new DBLocal(getActivity());
        txtNom.setText(MainActivity.user.getNom());
        txtPrenom.setText(MainActivity.user.getPrenom());
        txtAdresse.setText(MainActivity.user.getAdresse());
        txtTelephone.setText(MainActivity.user.getTelephone());
        txtLoginProfil.setText(MainActivity.user.getLogin());
        txtPasswordProfil.setText(MainActivity.user.getPassword());

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login = txtLoginProfil.getText().toString().trim();
                password = txtPasswordProfil.getText().toString().trim();
                telephone = txtTelephone.getText().toString().trim();
                adresse = txtAdresse.getText().toString().trim();
                nom = txtNom.getText().toString().trim();
                prenom = txtPrenom.getText().toString().trim();

               boolean val = dbLocal.updateUser(MainActivity.user.getLogin(), login, password, nom, prenom, adresse, telephone);
               if(val) {
                   Toast.makeText(getActivity(), "Enregistrement réussi", Toast.LENGTH_SHORT).show();

                   MainActivity.user= dbLocal.getUser(login, password);
               }
               else
                   Toast.makeText(getActivity(), "Enregistrement échoué", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}