package com.nilesh.moneymarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockDetailsAdapter extends RecyclerView.Adapter<StockDetailsAdapter.ViewHolder> {

    private Context context;
    private List<StockDetailsModel> stockDetailsList;

    public StockDetailsAdapter(Context context, List<StockDetailsModel> stockDetailsList) {
        this.context = context;
        this.stockDetailsList = stockDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_stockdetails, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockDetailsModel stockDetails = stockDetailsList.get(position);

        holder.symbolTextView.setText(stockDetails.getSymbol());
        holder.identifierTextView.setText(stockDetails.getIdentifier());
        holder.openTextView.setText(stockDetails.getOpen());
        holder.dayHighTextView.setText(stockDetails.getDayHigh());
        holder.dayLowTextView.setText(stockDetails.getDayLow());
        holder.lastPriceTextView.setText(stockDetails.getLastPrice());
        holder.previousCloseTextView.setText(stockDetails.getPreviousClose());

        // Set other data as needed

        // For strings after "Previous Close"
        holder.changeTextView.setText(stockDetails.getChange());
        holder.pChangeTextView.setText(stockDetails.getPChange());
        holder.yearHighTextView.setText(stockDetails.getYearHigh());
        holder.yearLowTextView.setText(stockDetails.getYearLow());
        holder.totalTradedVolumeTextView.setText(stockDetails.getTotalTradedVolume());
        holder.totalTradedValueTextView.setText(stockDetails.getTotalTradedValue());
        holder.perChange365dTextView.setText(stockDetails.getPerChange365d());
        holder.perChange30dTextView.setText(stockDetails.getPerChange30d());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stockDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView symbolTextView, identifierTextView, openTextView, dayHighTextView, dayLowTextView,
                lastPriceTextView, previousCloseTextView, changeTextView, pChangeTextView,
                yearHighTextView, yearLowTextView, totalTradedVolumeTextView, totalTradedValueTextView,
                perChange365dTextView, perChange30dTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            symbolTextView = itemView.findViewById(R.id.symbolTextView);
            identifierTextView = itemView.findViewById(R.id.identifierTextView);
            openTextView = itemView.findViewById(R.id.openTextView);
            dayHighTextView = itemView.findViewById(R.id.dayHighTextView);
            dayLowTextView = itemView.findViewById(R.id.dayLowTextView);
            lastPriceTextView = itemView.findViewById(R.id.lastPriceTextView);
            previousCloseTextView = itemView.findViewById(R.id.previousCloseTextView);

            // Initialize other TextViews as needed

            // For strings after "Previous Close"
            changeTextView = itemView.findViewById(R.id.changeTextView);
            pChangeTextView = itemView.findViewById(R.id.pChangeTextView);
            yearHighTextView = itemView.findViewById(R.id.yearHighTextView);
            yearLowTextView = itemView.findViewById(R.id.yearLowTextView);
            totalTradedVolumeTextView = itemView.findViewById(R.id.totalTradedVolumeTextView);
            totalTradedValueTextView = itemView.findViewById(R.id.totalTradedValueTextView);
            perChange365dTextView = itemView.findViewById(R.id.perChange365dTextView);
            perChange30dTextView = itemView.findViewById(R.id.perChange30dTextView);
        }
    }
}
