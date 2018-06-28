package com.samsson.motornation.utils;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.samsson.motornation.delegates.CustomDelegates;

import java.util.ArrayList;
import java.util.HashMap;
import cz.msebera.android.httpclient.Header;

/**
 * Created by pc7 on 5/31/2017.
 */

public class MultiServerRequest {
    private static AsyncHttpClient client = new AsyncHttpClient();
    final int DEFAULT_TIMEOUT = 80 * 1000;
    CustomDelegates customDelegates;

    public MultiServerRequest(CustomDelegates customDelegates, Context cxt) {
        this.customDelegates = customDelegates;
        client.setConnectTimeout(DEFAULT_TIMEOUT);
        client.setResponseTimeout(DEFAULT_TIMEOUT);
    }
    public void postData(HashMap<String, String> parameters, final String controllerName) {
        StringBuilder serverUrl = new StringBuilder();
        serverUrl.append(Constantvariables.BASE_URL);
        serverUrl.append(controllerName);

        RequestParams params = new RequestParams(parameters);
        client.post(serverUrl.toString(), params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                //Log.i("Success response: ",responseString);
                customDelegates.multiResponseFromApi(responseString,controllerName);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                customDelegates.onErrorResponse();
            }
        });
    }
    public void getdata(final String controllerName)
    {
        StringBuilder serverUrl = new StringBuilder();
        serverUrl.append(Constantvariables.BASE_URL);
        serverUrl.append(controllerName);

        client.get(serverUrl.toString(), new AsyncHttpResponseHandler()
        {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                //Log.i("Success response: ",responseString);
                customDelegates.multiResponseFromApi(responseString,controllerName);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                customDelegates.onErrorResponse();
            }


        });
    }

    public void postDataWithList(HashMap<String, String> parameters, ArrayList<String> arrayList, String customParamsKey, final String controllerName) {
        StringBuilder serverUrl = new StringBuilder();
        serverUrl.append(Constantvariables.BASE_URL);
        serverUrl.append(controllerName);

        RequestParams params = new RequestParams(parameters);
        for (int i = 0; i< arrayList.size(); i++){
            params.put(customParamsKey,arrayList.get(i));
        }
        client.post(serverUrl.toString(), params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                //Log.i("Success response: ",responseString);
                customDelegates.multiResponseFromApi(responseString,controllerName);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                customDelegates.onErrorResponse();
            }
        });

    }
}
