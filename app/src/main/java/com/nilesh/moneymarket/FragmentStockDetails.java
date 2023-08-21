package com.nilesh.moneymarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentStockDetails extends Fragment {

    private RecyclerView recyclerView;
    private StockDetailsAdapter adapter;
    private List<StockDetailsModel> stockDetailsList;
    private RequestQueue requestQueue;

    String selectedItemData;


    public FragmentStockDetails() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(requireContext());
        // Get the selected symbol from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            String selectedSymbol = bundle.getString("selected_symbol");
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stockdetails, container, false);

        recyclerView = rootView.findViewById(R.id.stockdet_recyc);
        stockDetailsList = new ArrayList<>();
        adapter = new StockDetailsAdapter(getContext(), stockDetailsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedItemData = bundle.getString("selectedItemData");
            // Now you can use the selectedItemData to populate your views or perform other actions
        }

        fetchStockData();
        //fetchDataUsingVolley();
        return rootView;
    }

    private void fetchStockData() {
        String url = "https://nileshsinghdahiya.in/admin-panle/singlestock.php?symbol=" + selectedItemData;

//        String demo_url = "https://nileshsinghdahiya.in/admin-panle/symbolapi.php"; // Replace with your API URL
//        int maxEntries = 4; // Display only the first 4 entries
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String symbol = jsonObject.getString("symbol");
                            String identifier = jsonObject.getString("identifier");
                            String open = jsonObject.getString("open");
                            String dayHigh = jsonObject.getString("dayHigh");
                            String dayLow = jsonObject.getString("dayLow");
                            String lastPrice = jsonObject.getString("lastPrice");
                            String previousClose = jsonObject.getString("previousClose");
                            String change = jsonObject.getString("change");
                            String pChange = jsonObject.getString("pChange");
                            String yearHigh = jsonObject.getString("yearHigh");
                            String yearLow = jsonObject.getString("yearLow");
                            String totalTradedVolume = jsonObject.getString("totalTradedVolume");
                            String totalTradedValue = jsonObject.getString("totalTradedValue");
                            String perChange365d = jsonObject.getString("perChange365d");
                            String perChange30d = jsonObject.getString("perChange30d");
                            //String dayHigh = jsonObject.getString("dayHigh");
                            stockDetailsList.add(new StockDetailsModel(symbol, identifier, open, dayHigh, dayLow, lastPrice, previousClose, change, pChange, yearHigh, yearLow, totalTradedVolume, totalTradedValue, perChange365d, perChange30d));
                            adapter.notifyDataSetChanged();
                        }

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


//    private void fetchDataUsingVolley() {
//        String url = "YOUR_API_URL_HERE"; // Replace with your API URL
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Process the JSON response and populate stockDetailsList
//                        parseJsonResponse(response);
//
//                        // Notify the adapter that data has changed
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                    }
//                });
//
//        // Add the request to the RequestQueue
//        requestQueue.add(request);
//    }
//
//    private void parseJsonResponse(JSONObject response) {
//        // Parse the JSON response and populate the stockDetailsList
//        // Example: Assume the JSON response contains an array of stock details
//        try {
//            JSONArray jsonArray = response.getJSONArray("stock_details");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject stockJson = jsonArray.getJSONObject(i);
//
//                    JSONObject jsonObject = response.getJSONObject(String.valueOf(i));
//                    String symbol = jsonObject.getString("symbol");
//                    String identifier = jsonObject.getString("identifier");
//                    String open = jsonObject.getString("open");
//                    String dayHigh = jsonObject.getString("dayHigh");
//                    String dayLow = jsonObject.getString("dayLow");
//                    String lastPrice = jsonObject.getString("lastPrice");
//                    String previousClose = jsonObject.getString("previousClose");
//                    String change = jsonObject.getString("change");
//                    String pChange = jsonObject.getString("pChange");
//                    String yearHigh = jsonObject.getString("yearHigh");
//                    String yearLow = jsonObject.getString("yearLow");
//                    String totalTradedVolume = jsonObject.getString("totalTradedVolume");
//                    String totalTradedValue = jsonObject.getString("totalTradedValue");
//                    String perChange365d = jsonObject.getString("perChange365d");
//                    String perChange30d = jsonObject.getString("perChange30d");
//                    //String dayHigh = jsonObject.getString("dayHigh");
//
//                    stockDetailsList.add(new StockDetailsModel(symbol,identifier,open,dayHigh,dayLow,lastPrice,previousClose,change,pChange,yearHigh,yearLow,totalTradedVolume,totalTradedValue,perChange365d,perChange30d));
//                }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
