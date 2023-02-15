package com.example.leyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import com.loopj.android.http.*;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText truckno, password;
    private Button login;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        truckno =  findViewById(R.id.textField1);
        password  = findViewById(R.id.textField2);
        login = findViewById(R.id.filledButton);
        loadingBar = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trucknum = truckno.getText().toString().trim();
                String pass = password.getText().toString().trim();



                if(trucknum == ""){
                    truckno.setError("Enter Truck Number");
                    truckno.setFocusable(true);
                }
                else{
                    try {
                        loginUser(trucknum, pass);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }






            }
        });
    }

    private void loginUser(String trucknum, String pass) throws UnsupportedEncodingException {
//        loadingBar.setMessage("Logging In....");
//        loadingBar.show();

        //TODO- check w Aditya


        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonObject = new JSONObject();
        String url = "http://172.25.32.21:3000/truck/login";




        try {
            jsonObject.put("truckNumber", trucknum);
            jsonObject.put("password", pass);
            String entjson = String.valueOf(jsonObject);
            Toast.makeText(LoginActivity.this, entjson, Toast.LENGTH_LONG).show();
            StringEntity entity = new StringEntity(jsonObject.toString());
            String entstr = String.valueOf(entity);
            Toast.makeText(LoginActivity.this, entstr, Toast.LENGTH_LONG).show();
            client.post(this, url, entity, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        Log.d("POST Response: ", new JSONObject(new String(responseBody)).toString());
                        Toast.makeText(LoginActivity.this, "Welcome LeyFit User ", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MapsActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    // Put exception handling logic here
                    Toast.makeText(LoginActivity.this, "Some error ", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception ex) {
            // json exception
            Toast.makeText(LoginActivity.this, "JSON error ", Toast.LENGTH_SHORT).show();
        }







    }
}