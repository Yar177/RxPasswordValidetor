package com.yar.passwordvalidetor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Password Validated", Toast.LENGTH_LONG).show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();

            }
        });



    }
}
