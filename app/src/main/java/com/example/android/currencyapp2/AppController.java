package com.example.android.currencyapp2;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by NORMAL on 10/13/2017.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    public RequestQueue requestQueue;
    private static AppController mInstance;
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance(Context context1) {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;

    }
    public <T> void addToRequestQueue(Request<T>request, String tag) {
        request.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getRequestQueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T>request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }


}
