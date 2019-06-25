package com.yar.passwordvalidetor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    EditText createPassword;
    EditText confirmPassword;

    Button submit;
    Button cancel;

    TextView listView;



    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = (TextView) findViewById(R.id.search_results);



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


        RxTextView.textChanges(createPassword)
                .doOnNext(text -> this.clearSearchResults())
                .filter(text -> text.length() >= 3)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateSearchResults);

    }

    private void clearSearchResults() {

        listView.setText("");

        listView.setVisibility(View.INVISIBLE);


    }


    private void updateSearchResults(CharSequence text){
        listView.setVisibility(View.VISIBLE);


        StringBuilder list = new StringBuilder();

        for (int i=0; i<4; i++){
            list.append("" + text + Math.random() + "\n");
        }

        listView.setText("");
        listView.setText(list);


    }



}
