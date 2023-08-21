package com.nilesh.moneymarket;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StockAdapter extends ArrayAdapter<StockModel> {
    public StockAdapter(Context context, List<StockModel> stocks) {
        super(context, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.custom_grid1, null);
        }

        StockModel stock = getItem(position);

        TextView symbolTextView = convertView.findViewById(R.id.titleTextView);
        TextView identifierTextView = convertView.findViewById(R.id.identifierTextView);
        TextView openPriceTextView = convertView.findViewById(R.id.openPriceTextView);
        TextView dayHighTextView = convertView.findViewById(R.id.dayHighTextView);

        symbolTextView.setText(stock.getSymbol());
        identifierTextView.setText(stock.getIdentifier());
        openPriceTextView.setText(String.valueOf(stock.getOpenPrice()));
        dayHighTextView.setText(String.valueOf(stock.getDayHigh()));

        return convertView;
    }
}