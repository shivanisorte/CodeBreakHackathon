package com.example.leyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

//import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
//                Log.d("success", String.valueOf(responseBody));
                Toast.makeText(MainActivity.this, String.valueOf(responseBody), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });








    }
}