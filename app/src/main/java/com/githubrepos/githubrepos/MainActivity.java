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
   static int stargazers_count=0;
    static int watchers=0;
  static   String name = null;

   // ArrayList<String> reponame = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View button = findViewById(R.id.button);
        button.setOnClickListener(this);


        findViewById(R.id.marker_progress).setVisibility(View.GONE);

          // if you creata toast you do it like this
       // Toast.makeText(context, message, duration).show();

       // context is almost always the activity which started that operation,
         //       so in most cases you need to tell the action which activity called it
           //     you can give context in the form of both Activity class and Context class

             //   in the above case, you can do both:

//                Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
  //      Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
    //    Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
      //  Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT).show();

        // all three are correct.
        // 'this' is equal to 'MAinActivity.this'
        // this represents the current activity (mostly)
        // when in any class, you call 'this', to represent the current instance of the class as a whole, in this case the class is Activity is liye you can send it as a context
        //  so when you call a function from within the same class,
        // for example if i call onClickView(someview), it is the same as this.onClickView

        // acha, now each activity has a little piece of information called context, which can be called by th efunction getApplicationContext() in an activity
        //sometimes functions need context sometimes they need the reference oof the whole activity

        // itni baat samajh aa gai?

        //delete these lines lates




    }


    public void onClick(View view) {
                findViewById(R.id.marker_progress).setVisibility(View.VISIBLE);
                hideKeyboard();
                    final TextView ans = (TextView) findViewById(R.id.textView1);
                        final EditText edit = (EditText) findViewById(R.id.editText);
                        String a = edit.getText().toString();

                         RepoData.path = "/users/" + a + "/repos";

                            RepoData.username = a;








                APIClient.get(RepoData.path, null, new JsonHttpResponseHandler() {



                    @Override
                    public void onStart() {
                        ans.setText("Processing");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray
                            response) {
                        // 'JSONArray response' because it will be an array of repos
                        findViewById(R.id.marker_progress).setVisibility(View.GONE);

                        RepoData.reponame = new ArrayList<String>();
                        RepoData.repoStars= new ArrayList<String>();
                        RepoData.repoWatchers= new ArrayList<String>();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = null;
                            try {
                                obj = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                name = obj.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            RepoData.reponame.add(name);





                        }

                        for (int i=0; i<3;i++){


                        }



                            //this adds an element to the list.

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj1 = null;
                            try {
                                obj1 = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                stargazers_count = obj1.getInt("stargazers_count");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            String x;

                            x= String.valueOf(stargazers_count);


                            RepoData.repoStars.add(x);
                        }



                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj2 = null;
                            try {
                                obj2 = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                watchers = obj2.getInt("watchers");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            String x;

                            x= String.valueOf(watchers);


                            RepoData.repoWatchers.add(x);
                        }








                      Intent i = new Intent(getApplicationContext(), hidden.class);
                      startActivity(i);


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







