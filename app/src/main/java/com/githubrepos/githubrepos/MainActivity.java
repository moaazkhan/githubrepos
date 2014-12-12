package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {
   static int n=0;
    ArrayList<String> mylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ed1;
        final Button button = (Button) findViewById(R.id.button);
        final EditText edit = (EditText) findViewById(R.id.editText);
        final TextView ans = (TextView) findViewById(R.id.textView);
       final String owner ;

        final String[] nu1 = {"1:","2:","3:","4:"};


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a = edit.getText().toString();
                String path = "/users/" + a + "/repos";

                Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();

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
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        ans.setText("Error:\n\n" + responseString);


                    }
                });

            }
        });


    }


}
