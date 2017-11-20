package com.example.android.currencyapp2;

/**
 * Created by NORMAL on 11/7/2017.
 */

public class CurrencyDetails {
    public String flag;
    public double btcValue, ethValue;

    public CurrencyDetails(String flag, double btcValue, double ethValue) {
        this.flag = flag;
        this.btcValue = btcValue;
        this.ethValue = ethValue;
    }

    public String getFlag() {
        return flag;
    }
    public Double getBtcValue() {
        return btcValue;
    }
    public Double getEthValue() {
        return ethValue;
    }
}
