package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class hidden extends Activity {
    ArrayList<String> mylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String value = extras.getString("new_variable_name");


            APIClient.get(value, null, new JsonHttpResponseHandler() {
                // GET doesnt need params
                // BUT IT DOES NEED A PROPER PATH

                final TextView ans = (TextView) findViewById(R.id.textView1);

                @Override
                public void onStart() {
                    ans.setText("Processing mofo");
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray
                        response) {
                    // 'JSONArray response' because it will be an array of repos


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String name = null;
                        try {
                            name = obj.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        mylist.add(name); //this adds an element to the list.


                    }
                    String x = TextUtils.join("\n", mylist);

                    ans.setText(x);

                    mylist = new ArrayList<String>();
                }

                @Override
                //  public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject error) {
                    ans.setText("Error:\n\n" + error);


                }

            });
        }
    }
















}
