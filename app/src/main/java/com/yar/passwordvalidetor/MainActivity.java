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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    EditText createPassword;
    EditText confirmPassword;

    Button submit;
    Button cancel;

    TextView listView;






    Pattern range = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()+]{8,50}$");
    Pattern numberMatch = Pattern.compile(".*[0-9].*");
    Pattern specialMatch = Pattern.compile("^(?=[\\w!@#$%^&*()+])(?:.*[!@#$%^&*()+]+.*)$");
    Pattern upperMatch = Pattern.compile(".*[A-Z].*");
    Pattern lowerMatch = Pattern.compile(".*[a-z].*");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
               // .filter(text -> text.length() >= 3)
               // .debounce(500, TimeUnit.MILLISECONDS)
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

        Matcher rangeMatcher = range.matcher(text);
        Matcher numberMatcher = numberMatch.matcher(text);
        Matcher specialMatcher = specialMatch.matcher(text);
        Matcher upperMatcher = upperMatch.matcher(text);
        Matcher lowerMatcher = lowerMatch.matcher(text);


        if (!rangeMatcher.matches()){
            list.append("Minimum of 8 characters long and contain at least 2 of the following: \n");
        }
        if (!numberMatcher.matches()){
            list.append("Use a number (0-9)\n");
        }
        if (!specialMatcher.matches()){
            list.append("Use a special characters (@#$%)\n");
        }
        if (!upperMatcher.matches()){
            list.append("Use an uppercase character (A-Z)\n");
        }
        if (!lowerMatcher.matches()){
            list.append("Use an uppercase character (A-Z)\n");
        }




//        for (int i=0; i<4; i++){
//            list.append("" + text + Math.random() + "\n");
//        }

        listView.setText("");
        listView.setText(list);


    }



}
