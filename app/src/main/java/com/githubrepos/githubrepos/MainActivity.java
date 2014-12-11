package com.githubrepos.githubrepos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ed1;
        final Button button = (Button)findViewById(R.id.button);
        final EditText edit = (EditText)findViewById(R.id.editText);
        final TextView ans = (TextView)findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a= edit.getText().toString();
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
                        ans.setText("success");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        ans.setText("Error:\n\n" + responseString);

                        // Issue(s):
                        // ----------
                        // Your call was failing, but the onFailure wasn't being called.
                        // It shouldn't have failed since your code was correct, and the call was fine too
                        //
                        // The issue was that Github has a new policy that requires a 'User-Agent' header.
                        // Chrome's postman was automatically specifying it, but it needs to be put in manually in apps.
                        // Fuck that. Don't worry about it, saara chutiyapa hai. There are less than 5% APIs that use it
                        // An apparently Github is one of them
                        // > http://jmp.sh/gmYmR3p
                        //
                        // So WHY THE FUCK was onFailure not being called?
                        // Because the fuckers at github were sending this response in simple text instead of json
                        // I didn't want to confuse you at first but there are different types of onSuccess and onFailure
                        // methods.
                        // Updated gist: https://gist.github.com/sheharyarn/950561cd3d4487f69e46

                        // BUT IT IS STILL NOT WORKING.
                        // Yes, I know. Because YOU will make it work.
                        // First run it as is.
                        // You'll see the error being returned when you hit the button in app
                        // Now open APIClient.java and uncomment the new line I added
                        // It adds the fucking User-Agent Header
                        // Now the app will work

                        // Also, logs are your best friend
                    }
                });

            }
        });

    }

}
