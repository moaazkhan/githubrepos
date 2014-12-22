package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener{
   static int n=0;
    ArrayList<String> mylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ed1;

        View button = findViewById(R.id.button);
        button.setOnClickListener(this);

        final EditText edit = (EditText) findViewById(R.id.editText);
        final TextView ans = (TextView) findViewById(R.id.textView1);
        final String owner;

        }



            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.button:
                        //Get References to the TextView

                        final EditText edit = (EditText) findViewById(R.id.editText);
                        String a = edit.getText().toString();
                        String path = "/users/" + a + "/repos";

                        Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();


                        LinearLayout hiddenLayout = (LinearLayout) findViewById(R.id.hiddenLayout);
                        if (hiddenLayout == null) {
                            //Inflate the Hidden Layout Information View
                            LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout2);
                            View hiddenInfo = getLayoutInflater().inflate(R.layout.hidden, myLayout, false);
                            myLayout.addView(hiddenInfo);
                        }
                        final TextView ans = (TextView) findViewById(R.id.textView1);
                        





                        APIClient.get(path, null, new JsonHttpResponseHandler() {
                            // GET doesnt need params
                            // BUT IT DOES NEED A PROPER PATH

                            @Override
                            public void onStart() {
                                ans.setText("Processing mofo");
                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
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
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject error){
                                ans.setText("Error:\n\n" + error);


                            }

                        });

















                }
            }






}
