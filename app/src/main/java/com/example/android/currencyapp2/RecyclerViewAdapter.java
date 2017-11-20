package com.example.android.currencyapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;


/**
 * Created by NORMAL on 11/7/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.MyViewHolder>{

    private List<CurrencyDetails> currencyDetails;
    private Context context;
    private LayoutInflater inflater;




    public RecyclerViewAdapter(Context context, List<CurrencyDetails> currencyDetails) {

        this.context = context;
        this.currencyDetails = currencyDetails;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        CurrencyDetails currency = currencyDetails.get(position);
        viewHolder.currencyCode.setText(currency.getFlag());
        viewHolder.btcValue.setText(String.format("%1$,.2f", currency.getBtcValue()));
        viewHolder.ethValue.setText(String.format("%1$,.2f", currency.getEthValue()));

    }

    @Override
    public int getItemCount() {
        return currencyDetails.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyCode, btcValue, ethValue;

        public MyViewHolder(View rootView) {
            super(rootView);
            currencyCode = (TextView) rootView.findViewById(R.id.currencyCode);
            btcValue = (TextView) rootView.findViewById(R.id.btcValue);
            ethValue = (TextView) rootView.findViewById(R.id.ethValue);

            //clicking each item in the list

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        CurrencyDetails theCurrency = currencyDetails.get(pos);
                        Intent intent = new Intent(context, ConversionActivity.class);
                        intent.putExtra(InitialValues.INITIAL_CURRENCY_CODE, currencyDetails.get(pos).getFlag());
                        intent.putExtra(InitialValues.INITIAL_BTC_VALUE, currencyDetails.get(pos).getBtcValue());
                        intent.putExtra(InitialValues.INITIAL_ETH_VALUE, currencyDetails.get(pos).getEthValue());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"Converting" + theCurrency.getFlag(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
