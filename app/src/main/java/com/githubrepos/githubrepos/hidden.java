package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class hidden extends Activity {



    static ArrayAdapter<String> listAdapter ;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);



            ArrayList<String> urls;
            urls = getIntent().getStringArrayListExtra("new_variable_name");



            listView = (ListView) findViewById(R.id.list);




            // Defined Array values to show in ListView



            listAdapter = new ArrayAdapter<String>(hidden.this, R.layout.simplerow, urls);
            listView.setAdapter(listAdapter);




        }
    }
















