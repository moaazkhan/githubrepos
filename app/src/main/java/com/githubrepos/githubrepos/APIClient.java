package com.githubrepos.githubrepos;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class APIClient {
    private static final String BASE_URL  = "https://api.github.com";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("User-Agent", "hi");
        client.get(getAbsoluteUl(url), params, responseHandler);
    }

    private static String getAbsoluteUl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
