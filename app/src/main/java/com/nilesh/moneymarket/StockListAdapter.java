package com.nilesh.moneymarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StockListAdapter extends ArrayAdapter<StockModel> {

    private List<StockModel> originalData;
    private List<StockModel> filteredData;
    private LayoutInflater inflater;




    public StockListAdapter(Context context, List<StockModel> data) {
        super(context,0,data);
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
        this.inflater = LayoutInflater.from(context);



    }



    public void filterList(List<StockModel> filteredList) {
        filteredData = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public StockModel getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_grid1, parent, false);
            holder = new ViewHolder();
            holder.symbolTextView = convertView.findViewById(R.id.titleTextView);
            holder.identifierTextView = convertView.findViewById(R.id.identifierTextView);
            holder.openPriceTextView = convertView.findViewById(R.id.openPriceTextView);
            holder.dayHighTextView = convertView.findViewById(R.id.dayHighTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StockModel stock = getItem(position);

        holder.symbolTextView.setText(stock.getSymbol());
        holder.identifierTextView.setText(stock.getIdentifier());
        holder.openPriceTextView.setText(String.valueOf(stock.getOpenPrice()));
        holder.dayHighTextView.setText(String.valueOf(stock.getDayHigh()));

        return convertView;
    }

    private static class ViewHolder {
        TextView symbolTextView;
        TextView identifierTextView;
        TextView openPriceTextView;
        TextView dayHighTextView;
    }
}