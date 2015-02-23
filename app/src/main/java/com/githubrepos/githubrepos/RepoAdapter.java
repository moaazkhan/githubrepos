package com.githubrepos.githubrepos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Like every other class, repo adapter is your custom class taht extends 'something'
 * in our case, we will extend baseadapter class
 *
 * right now it's giving error because sometimes when you extend a class, it
 *
 */
public class RepoAdapter extends BaseAdapter {
    private ArrayList<String> repos;
    private Activity myactivity;
    private LayoutInflater inflater;

    public RepoAdapter(Activity activity, ArrayList<String> reponames) {
        myactivity = activity;
        repos = reponames;
    }

    public int getCount() {
        return repos.size();
    }
    public long getItemId(int pos) {
        return pos;
    }
    public Object getItem(int pos) {
        return repos.get(pos);
    }

    public View getView(int position, View rowView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) myactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (rowView == null)
            rowView = inflater.inflate(R.layout.item, parent, false);

        TextView repoText = (TextView)rowView.findViewById(R.id.repoText);
        TextView forkText = (TextView)rowView.findViewById(R.id.forkText);
        TextView starText = (TextView)rowView.findViewById(R.id.starText);

        repoText.setText(RepoData.reponame.get(position));
        forkText.setText(RepoData.repoWatchers.get(position));
        starText.setText(RepoData.repoStars.get(position));


        return rowView;

    }
}
