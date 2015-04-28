package com.mapia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {
    EditText edtID, edtPW;
    Button btnLogin;
    TextView txtSignup, txtHelp;


    protected void loginCheck(String id, String pw){

        if(id.compareTo("admin")==0 && pw.compareTo("admin")==0){
            Intent i = new Intent(LoginActivity.this, MapActivity.class);
            startActivity(i);
            finish();
        }
        else{

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mapia.R.layout.activity_login);

        edtID = (EditText)findViewById(com.mapia.R.id.edtID);
        edtPW = (EditText)findViewById(com.mapia.R.id.edtPW);

        btnLogin = (Button)findViewById(com.mapia.R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean loginFlag = false;

                loginCheck(edtID.getText().toString(), edtPW.getText().toString());
            }
        });

        txtSignup = (TextView)findViewById(com.mapia.R.id.txtSignUpRequest);
        txtSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        txtHelp = (TextView)findViewById(com.mapia.R.id.txtHelpRequest);
        txtHelp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call Website ./Help
            }
        });
    }
}