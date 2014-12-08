package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ed1;
        final Button button = (Button)findViewById(R.id.button);
        final EditText edit = (EditText)findViewById(R.id.editText);
        final TextView ans = (android.widget.TextView)findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a= edit.getText().toString();
                String path = "/users/" + a + "/repos";

                APIClient.get(path, null, new JsonHttpResponseHandler() {
                    // GET doesnt need params
                    // BUT IT DOES NEED A PROPER PATH
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                     ans.setText("success");
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        ans.setText("Error");
                    }
                });

            }
        });




    }



}
