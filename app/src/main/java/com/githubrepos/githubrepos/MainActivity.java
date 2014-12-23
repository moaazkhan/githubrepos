package com.githubrepos.githubrepos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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


        View button = findViewById(R.id.button);
        button.setOnClickListener(this);


        findViewById(R.id.marker_progress).setVisibility(View.GONE);


        }



            public void onClick(View view) {
                findViewById(R.id.marker_progress).setVisibility(View.VISIBLE);
                hideKeyboard();
                    final TextView ans = (TextView) findViewById(R.id.textView1);
                        final EditText edit = (EditText) findViewById(R.id.editText);
                        String a = edit.getText().toString();
                        String path = "/users/" + a + "/repos";







                APIClient.get(path, null, new JsonHttpResponseHandler() {
                    // GET doesnt need params
                    // BUT IT DOES NEED A PROPER PATH



                    @Override
                    public void onStart() {
                        ans.setText("Processing");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray
                            response) {
                        // 'JSONArray response' because it will be an array of repos
                        findViewById(R.id.marker_progress).setVisibility(View.GONE);

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

                        Intent i = new Intent(getApplicationContext(), hidden.class);
                        i.putExtra("new_variable_name",mylist);
                        startActivity(i);
                        mylist.clear();


                    }

                    @Override
                    //  public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject error) {
                        findViewById(R.id.marker_progress).setVisibility(View.GONE);
                        ans.setMovementMethod(new ScrollingMovementMethod());
                        ans.setText("Error:\n\n" + error.toString());




                    }

                });

                }
    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}







