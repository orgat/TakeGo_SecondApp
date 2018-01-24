package com.revenant.takego_secondapp.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;
import com.revenant.takego_secondapp.model.entities.Customer;

import java.util.List;

public class LogInActivity extends Activity {
    private Button register;
    private Button logIn;
    private EditText id;
    private EditText password;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        findViews();
        sharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idInput = id.getText().toString().replace(" ", "");
                final String passwordInput = password.getText().toString().replace(" ", "");

                if (idInput.isEmpty() || passwordInput.isEmpty()) { //User did not enter username or password
                    Toast.makeText(getBaseContext(), "Please enter ID and password and try again.", Toast.LENGTH_SHORT).show();
                } else {   //User entered both ID and Password
                   String result = sharedPreferences.getString(idInput,"0");
                   if(passwordInput.equals(result) && !passwordInput.equals("0")){ //Username and password are valid. go to MainActivity.
                       goToMainActivity(idInput);
                   } else{
                      new AsyncTask<Void,Void,Customer>(){
                          @Override
                          protected void onPostExecute(Customer customer) {
                              super.onPostExecute(customer);
                              if(customer!=null){ //Customer found in the database.
                                  if(customer.getPassword().equals(passwordInput)){ //Correct password
                                      editor.putString(idInput,passwordInput);
                                      editor.commit();
                                      goToMainActivity(idInput);
                                  } else{ //Incorrect password
                                      Toast.makeText(getBaseContext(), "Wrong username or password.", Toast.LENGTH_SHORT).show();
                                  }
                              } else{ // Wrong username
                                  Toast.makeText(getBaseContext(), "Wrong username or password.", Toast.LENGTH_SHORT).show();
                              }
                          }

                          @Override
                          protected Customer doInBackground(Void... voids) {
                              try{
                                  return DBManagerFactory.getDB_SQL().findCustomerbyId(Long.valueOf(idInput));
                              }catch(Exception e){ //Something wrong with the ID
                                  return null;
                              }
                          }
                      }.execute();
                   }

                }


            }
        });
    }

    private void goToMainActivity(String idInput) {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        intent.putExtra(Constants.CustomerConst.ID,idInput);
        startActivity(intent);
    }

    private void findViews() {
        register = findViewById(R.id.registerId);
        logIn = findViewById(R.id.logInId);
        id = findViewById(R.id.idEditTextId);
        password = findViewById(R.id.passwordId);


    }
}
