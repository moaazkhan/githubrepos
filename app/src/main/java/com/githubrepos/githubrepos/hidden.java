package com.githubrepos.githubrepos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class hidden extends Activity {

    String[] x = {};
    String[] y = {};

    static ArrayAdapter<String> listAdapter ;
    ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);









            final ArrayList<String> value;
            value = RepoData.reponame;

        ArrayList<String> value2;
        value2 = RepoData.repoStars;

        ArrayList<String> value3;
        value3= RepoData.repoWatchers;

        //  edit.setText(value+value2);


        ArrayList<HashMap<String,Object>> listdata=new ArrayList<HashMap<String,Object>>();

          for (int i=0;i<value.size();i++){
              HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("title",value.get(i));
        hm.put("context","Stars: "+ value2.get(i));
        hm.put("context2","Watchers: "+value3.get(i));
        listdata.add(hm);
            }




        listView = (ListView) findViewById(R.id.list);

        SimpleAdapter adapter;
        adapter = new SimpleAdapter(this,listdata,
                R.layout.item, new String[] {"title",
                "context","context2"}, new int[] { R.id.reponame1,
                R.id.stars1,R.id.forks1 });

        listView.setAdapter(adapter);

        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long arg3) {


            //   String x;

             //  TextView repo1   = (TextView)container.findViewById(R.id.reponame1);
//
              // x= repo1.getText().toString();
               //

                Uri uri = Uri.parse("https://github.com/"+ RepoData.username+ "/" + RepoData.reponame.get(position) );
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

    }
















