package com.githubrepos.githubrepos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

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


                        final EditText edit = (EditText) findViewById(R.id.editText);
                        String a = edit.getText().toString();
                        String path = "/users/" + a + "/repos";
                Intent i = new Intent(getApplicationContext(), hidden.class);
                i.putExtra("new_variable_name",path);
                startActivity(i);


                }
            }







