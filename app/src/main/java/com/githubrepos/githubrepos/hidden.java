package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class hidden extends Activity {

static    ArrayList<String> mylist = new ArrayList<String>();

    static ArrayAdapter<String> listAdapter ;
    ListView listView ;

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



                @Override
                public void onStart() {
                        //ans.setText("Processing");

                    listView = (ListView) findViewById(R.id.list);




                    // Defined Array values to show in ListView
                    String[] values = new String[] { "Processing", "Hello"

                    };

                    ArrayList<String> planetList = new ArrayList<String>();
                    planetList.addAll( Arrays.asList(values) );
                    listAdapter = new ArrayAdapter<String>(hidden.this, R.layout.simplerow, planetList);
                    listView.setAdapter(listAdapter);







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


                    listView = (ListView) findViewById(R.id.list);




                    // Defined Array values to show in ListView



                    listAdapter = new ArrayAdapter<String>(hidden.this, R.layout.simplerow, mylist);
                    listView.setAdapter(listAdapter);


                }

                @Override
                //  public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject error) {
                    //ans.setText("Error:\n\n" + error);


                    listView = (ListView) findViewById(R.id.list);




                    // Defined Array values to show in ListView
                    String[] values = new String[] { "Error", error.toString()

                    };

                    ArrayList<String> planetList = new ArrayList<String>();
                    planetList.addAll( Arrays.asList(values) );
                    listAdapter = new ArrayAdapter<String>(hidden.this, R.layout.simplerow, planetList);
                    listView.setAdapter(listAdapter);


                }

            });


        }
    }
















}
