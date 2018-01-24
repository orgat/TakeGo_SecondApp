package com.revenant.takego_secondapp.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;

public class RegisterActivity extends Activity {
    private EditText id;
    private EditText name;
    private EditText lastName;
    private EditText phone;
    private EditText email;
    private EditText creditCard;
    private EditText password;
    private Button addCustomer;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        findViews();
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().replace(" ", "").isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter an ID number", Toast.LENGTH_SHORT).show();
                } else if(sharedPreferences.contains(id.getText().toString())){
                    Toast.makeText(getBaseContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                } else if(password.getText().toString().equals("0")){
                    Toast.makeText(getBaseContext(), "Your password cannot be '0'", Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().replace(" ", "").isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a password!", Toast.LENGTH_SHORT).show();
                } else {
                    addCustomer();
                }

            }
        });
    }

    private void clear() {
        id.setText("");
        name.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        creditCard.setText("");
        password.setText("");
    }

    private void addCustomer() {

        final ContentValues cv = new ContentValues();
        try {

            final long inputId=Long.valueOf(id.getText().toString());
            cv.put(Constants.CustomerConst.ID, inputId);
            cv.put(Constants.CustomerConst.NAME, name.getText().toString());
            cv.put(Constants.CustomerConst.LAST_NAME, lastName.getText().toString());
            cv.put(Constants.CustomerConst.PHONE_NUMBER, phone.getText().toString());
            cv.put(Constants.CustomerConst.EMAIL, email.getText().toString());
            cv.put(Constants.CustomerConst.CREDIT_CARD, creditCard.getText().toString());
            cv.put(Constants.CustomerConst.PASSWORD, password.getText().toString().replace(" ",""));


            new AsyncTask<Void, Void, Long>() {
                @Override
                protected void onPostExecute(Long aLong) {
                    if(aLong==null){
                        Toast.makeText(getBaseContext(), "Customer already exists!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getBaseContext(), "Customer: " + aLong + " added!", Toast.LENGTH_SHORT).show();
                        editor.putString(id.getText().toString(), password.getText().toString());
                        editor.commit();
                        finish();
                    }

                }

                @Override
                protected Long doInBackground(Void... voids) {
                    if(DBManagerFactory.getDB_SQL().findCustomerbyId(inputId)!=null)
                        return null;

                    return DBManagerFactory.getDB_SQL().addCustomer(cv);
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findViews() {
        id = findViewById(R.id.customerId);
        name = findViewById(R.id.nameId);
        lastName = findViewById(R.id.lastNameId);
        phone = findViewById(R.id.phoneId);
        email = findViewById(R.id.emailId);
        creditCard = findViewById(R.id.creditCardId);
        addCustomer = findViewById(R.id.addCustomerButtonId);
        password = findViewById(R.id.passwordId);
    }
}
