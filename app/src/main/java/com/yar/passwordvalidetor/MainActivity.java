package com.yar.passwordvalidetor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText createPassword;
    EditText confirmPassword;

    Button submit;
    Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createPassword = (EditText) findViewById(R.id.update_password_create_input_txt);
        confirmPassword = (EditText) findViewById(R.id.update_password_confirm_input_txt);

        submit = (Button) findViewById(R.id.password_update_submit_btn);
        cancel = (Button) findViewById(R.id.password_update_cancel_btn);



    }
}
