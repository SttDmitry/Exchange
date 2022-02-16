package com.base.exchange.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.exchange.R;
import com.base.exchange.entity.ExchangeRate;

import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {

    private List<ExchangeRate> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.exchangeInfo);
        }
    }


    public ExchangeAdapter(List<ExchangeRate> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ExchangeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exchange_example, parent, false);
        ExchangeAdapter.ViewHolder vh = new ExchangeAdapter.ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHolder holder, int position) {
        ExchangeRate exchangeRateTemp = mDataset.get(position);
        holder.info.setText(new StringBuilder(exchangeRateTemp.getNominal() + " " + exchangeRateTemp.getName() + " это " + exchangeRateTemp.getValue() + " руб.").toString());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
