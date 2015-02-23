package com.githubrepos.githubrepos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class hidden extends Activity {


    ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);




        listView = (ListView) findViewById(R.id.list);

        RepoAdapter adapter = new RepoAdapter(this, RepoData.reponame);

        listView.setAdapter(adapter);

       AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long arg3) {


                Uri uri = Uri.parse("https://github.com/"+ RepoData.username+ "/" + RepoData.reponame.get(position) );
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
   }

    }
















