package com.nilesh.moneymarket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockListFragment extends Fragment implements AdapterView.OnItemClickListener {


    private List<StockModel> stockList = new ArrayList<>();
    private GridView gridViewlist;
    private StockListAdapter adapter;
    private EditText searchEditText;

    public StockListFragment() {
    }

    public StockListFragment(StockModel selectedStock) {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stocklist, container, false);
        gridViewlist = rootView.findViewById(R.id.gridViewlist);
        searchEditText = rootView.findViewById(R.id.searchEditText);
        adapter = new StockListAdapter(getContext(), stockList);
        adapter.notifyDataSetChanged();
        gridViewlist.setAdapter(adapter);
        gridViewlist.setOnItemClickListener(this::onItemClick);


        // Add text change listener to the search EditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


        fetchStockData();
        return rootView;
    }



        private void fetchStockData() {
        String demo_url = "https://nileshsinghdahiya.in/admin-panle/symbolapi.php"; // Replace with your API URL
        int maxEntries = 4; // Display only the first 4 entries
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, demo_url, null,
                response -> {
                    try {
                        for (int i = 0; i <response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String symbol = jsonObject.getString("symbol");
                            String identifier = jsonObject.getString("identifier");
                            String openPrice = jsonObject.getString("open");
                            String dayHigh = jsonObject.getString("dayHigh");

                            stockList.add(new StockModel(symbol, identifier, openPrice, dayHigh));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void filter(String searchText) {
        List<StockModel> filteredList = new ArrayList<>();
        for (StockModel stock : stockList) {
            if (stock.getSymbol().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(stock);
            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Handle item click here
        StockModel stockModel = stockList.get(position);
        String selectedSymbol =stockModel.getSymbol();
        Bundle bundle = new Bundle();
        bundle.putString("selectedItemData", selectedSymbol); // Replace with your data

        // Create an instance of the destination fragment
        FragmentStockDetails destinationFragment = new FragmentStockDetails();
        destinationFragment.setArguments(bundle);

        // Navigate to the destination fragment
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, destinationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
