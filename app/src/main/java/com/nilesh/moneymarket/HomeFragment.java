package com.nilesh.moneymarket;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

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

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<StockModel> stockList = new ArrayList<>();
    private GridView gridView1;
    private StockAdapter adapter;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        gridView1 = rootView.findViewById(R.id.gridView1);
        adapter = new StockAdapter(getContext(), stockList);
        gridView1.setAdapter(adapter);
        gridView1.setOnItemClickListener(this::onItemClick);


        fetchStockData();
        return rootView;
    }

    private void fetchStockData() {
        String demo_url = "https://nileshsinghdahiya.in/admin-panle/symbolapi.php"; // Replace with your API URL
        int maxEntries = 4; // Display only the first 4 entries
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, demo_url, null,
                response -> {
                    try {
                        for (int i = 0; i < Math.min(response.length(), maxEntries); i++) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Handle item click here
        StockModel selectedStock = adapter.getItem(position);

        FragmentManager fm = getParentFragmentManager();
        // Replace the current fragment with a new fragment (you need to implement this part)
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, new StockListFragment(selectedStock));
        transaction.addToBackStack(null); // Optionally add the transaction to the back stack
        transaction.commit();
    }

}

