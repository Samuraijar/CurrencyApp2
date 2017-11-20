package com.example.android.currencyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by NORMAL on 11/8/2017.
 */

public class ConversionActivity extends AppCompatActivity {

    String currencyCode;
    String currencyFullName;

    EditText btcEdit, ethEdit, currencyEdit;
    TextView moneyCodeView;
    TextView fullNameView;
    Button btcConversion,ethConversion, currencyConversion, close;

    double btcRate;
    double ethRate;



    @Override
    protected void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.conversion_activity);
        moneyCodeView = (TextView) findViewById(R.id.money_code);
        fullNameView = (TextView) findViewById(R.id.full_name);

        btcEdit = (EditText) findViewById(R.id.btc_edit);
        ethEdit = (EditText) findViewById(R.id.eth_edit);
        currencyEdit = (EditText) findViewById(R.id.currency_edit);
        btcConversion = (Button) findViewById(R.id.convert_btc);
        ethConversion = (Button) findViewById(R.id.convert_eth);
        currencyConversion = (Button) findViewById(R.id.convert_currency);
        close = (Button) findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        currencyCode = intent.getStringExtra(InitialValues.INITIAL_CURRENCY_CODE);
        currencyFullName = getFullName(currencyCode);
        String conversionMessage = currencyFullName + " conversion";
        btcRate = intent.getDoubleExtra(InitialValues.INITIAL_BTC_VALUE, 0);
        ethRate = intent.getDoubleExtra(InitialValues.INITIAL_ETH_VALUE, 0);

        //these display the full name currency
        moneyCodeView.setText(currencyCode);
        fullNameView.setText(conversionMessage);
        if(getActionBar() != null) getActionBar().setTitle(currencyFullName);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(currencyFullName);


    }
    public String getFullName (String moneyCode) {
        switch (moneyCode) {
            case "USD": return "US Dollar";
            case "EUR": return "Euro";
            case "JPY": return "Japanese Yen";
            case "CHF": return "Swiss Franc";
            case "CAD": return "Canadian Dollar";
            case "AUD": return "Australian Dollar";
            case "HKD": return "Hong Kong Dollar";
            case "NGN": return "Nigeria Naira";
            case "CNY": return "Chinese Yuan";
            case "NZD": return "New Zealand Dollars";
            case "BRL": return "Brazilian Real ";
            case "KRW": return "South Korean Won";
            case "NOK": return "Norwegian Krone";
            case "GBP": return "Britain Pound Sterling";
            case "SEK": return "Swedish Krona";
            case "MXN": return "Mexican Peso";
            case "SDG": return "Singapore Dollar";
            case "INR": return "Indian Rupee";
            case "ZAR": return "South African Rand";
            case "INS": return "Israeli Shekel";
            default:return "";


        }
    }

    //in conversion view, entering an amount of a currency and clicking convert displays corresponding value of other currencies

    public void convertValue (View view) {
        if (view == btcConversion) {
            try {
                double btcDouble = Double.parseDouble(btcEdit.getText().toString());
                currencyEdit.setText(String.format("%1$,.2f", (btcDouble * btcRate)));
                ethEdit.setText(String.format("%1$,.2f", (btcDouble * (ethRate / btcRate))));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.scroll_view), InitialValues.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }else if (view == ethConversion) {

            try {
                double ethDouble = Double.parseDouble(ethEdit.getText().toString());
                currencyEdit.setText(String.format("%1$,.2f", (ethDouble * ethRate)));
                btcEdit.setText(String.format("%1$,.2f", (ethDouble * (btcRate / ethRate))));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.scroll_view), InitialValues.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        } else if (view == currencyConversion) {
            try {
                double currencyDouble = Double.parseDouble(currencyEdit.getText().toString());
                btcEdit.setText(String.format("%1$,.2f", (currencyDouble / btcRate)));
                ethEdit.setText(String.format("%1$,.2f", (currencyDouble / ethRate)));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.scroll_view), InitialValues.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }




        }



    }

}
