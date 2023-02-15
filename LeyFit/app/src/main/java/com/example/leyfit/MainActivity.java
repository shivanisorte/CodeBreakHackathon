package com.example.leyfit;

import static com.example.leyfit.R.id.txt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

//import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txt = findViewById(R.id.txt);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://172.25.32.21:3000/", new AsyncHttpResponseHandler() {



            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String str = new String(responseBody);
//                try {
//                    JSONObject testV=new JSONObject(str);
//                    String ans = testV.getString("name");
//                    txt.setText(ans);
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                txt.setText("Error in calling API");
            }


        });









    }


}