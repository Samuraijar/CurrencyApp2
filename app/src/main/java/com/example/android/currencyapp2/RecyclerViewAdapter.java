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


/**
 * Created by NORMAL on 11/7/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>{

    private List<CurrencyDetails> currencyDetails;
    private Context context;




    public RecyclerViewAdapter(Context context, List<CurrencyDetails> currencyDetails) {

        this.context = context;
        this.currencyDetails = currencyDetails;


    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        CurrencyDetails currency = currencyDetails.get(position);
        viewHolder.currencyCode.setText(currency.getFlag());
        viewHolder.btcValue.setText(String.format("%1$, .2f", currency.getBtcValue()));
        viewHolder.ethValue.setText(String.format("%1$, 2f", currency.getEthValue()));

    }

    @Override
    public int getItemCount() {
        return currencyDetails.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyCode, btcValue, ethValue;

        public ViewHolder(View view) {
            super(view);
            currencyCode = (TextView) view.findViewById(R.id.currencyCode);
            btcValue = (TextView) view.findViewById(R.id.btcValue);
            ethValue = (TextView) view.findViewById(R.id.ethValue);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        CurrencyDetails theCurrency = currencyDetails.get(pos);
                        Intent intent = new Intent(context, ConversionActivity.class);
                        intent.putExtra("currencyCode", currencyDetails.get(pos).getBtcValue());
                        intent.putExtra("currencyCode", currencyDetails.get(pos).getEthValue());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"Converting" + theCurrency.getFlag(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }













    }
}
