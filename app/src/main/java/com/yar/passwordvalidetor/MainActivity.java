package com.yar.passwordvalidetor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    EditText createPassword;
    EditText confirmPassword;

    Button submit;
    Button cancel;

    TextView listView;

    boolean meetsReqs = false;
    boolean match = false;

    int reqMet = 0;







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
                .doOnNext(text -> this.clearSearchResults(listView))
                .filter(text -> text.length() >= 1)
               // .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::validatePassword);


//        RxTextView.textChanges(confirmPassword)

    }

    private void clearSearchResults(TextView view) {
        //view.setText("");
        listView.setVisibility(View.GONE);
    }


    private void validatePassword(CharSequence text){
        listView.setVisibility(View.VISIBLE);
        StringBuilder list = new StringBuilder();



        Matcher rangeMatcher = range.matcher(text);
        Matcher numberMatcher = numberMatch.matcher(text);
        Matcher specialMatcher = specialMatch.matcher(text);
        Matcher upperMatcher = upperMatch.matcher(text);
        Matcher lowerMatcher = lowerMatch.matcher(text);


        if (!rangeMatcher.matches()){
            list.append("Minimum of 8 characters long ");
        }
        if (reqMet < 2){
            list.append("and contain at least 2 of the following:");
        }

        if (!numberMatcher.matches()){
            list.append("\nUse a number (0-9)");

        }else {
            reqMet++;
        }
        if (!specialMatcher.matches()){
            list.append("\nUse a special characters (@#$%)");
        }else {
            reqMet++;
        }
        if (!upperMatcher.matches()){
            list.append("\nUse an uppercase character (A-Z)");
        }else {
            reqMet++;
        }
        if (!lowerMatcher.matches()){
            list.append("\nUse an uppercase character (A-Z)");
        }else {
            reqMet++;
        }


        listView.setText("");
        listView.setText(list);


    }



}
