package com.example.android.currencyapp2;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    String urlJsonObj = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,JPY,CHF,CAD,AUD,HKD,NGN,CNY,NZD,BRL,KRW,NOK,GBP,SEK,MXN,SGD,INR,ZAR,INS";
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    List<CurrencyDetails> currencyDetails = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        recyclerViewAdapter = new RecyclerViewAdapter(this,  currencyDetails);
        recyclerView.setAdapter(recyclerViewAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //making internet call to get data..
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlJsonObj, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //parsing json object response using iterator to get json object nodes..

                        try {
                            JSONObject btc = response.getJSONObject("BTC".trim());
                            JSONObject eth = response.getJSONObject("ETH".trim());
                            Iterator<?> keysBTC = btc.keys();
                            Iterator<?> keysETH = eth.keys();

                            while (keysBTC.hasNext() && keysETH.hasNext()) {
                                String keyBTC = (String) keysBTC.next();
                                String keyETH = (String) keysETH.next();
                                CurrencyDetails cDetails = new CurrencyDetails(keyBTC, btc.getDouble(keyBTC), eth.getDouble(keyETH));
                                currencyDetails.add(cDetails);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            //telling recyclerview to update content..
                        } finally {
                            recyclerViewAdapter.notifyItemChanged(currencyDetails.size());

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();


            }

        });
        //this makes sure request runs in background

        requestQueue.add(jsonObjectRequest);
    }


}
