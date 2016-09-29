package com.example.zakar.logindesignbyzak;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends Fragment {

    TextView txtUsername;
    TextView txtPassword;
    TextView lblMessage;
    String username;
    String password;
    Button btnLogin;
    DataHandler dataHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        RelativeLayout login = (RelativeLayout) rootView.findViewById(R.id.login);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom);
        login.startAnimation(animation1);

        dataHandler = new DataHandler(getActivity());
        dataHandler.addUser("zak","123");
        txtUsername = (TextView) rootView.findViewById(R.id.txtUsername);
        txtPassword = (TextView) rootView.findViewById(R.id.txtPassword);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        lblMessage = (TextView) rootView.findViewById(R.id.lblMessage);

        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();

        dataHandler.insertEntry("zakariya","yeeee");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   lblMessage.setText(dataHandler.getSinlgeEntry(username));

            }


        });

        return rootView;
    }


}