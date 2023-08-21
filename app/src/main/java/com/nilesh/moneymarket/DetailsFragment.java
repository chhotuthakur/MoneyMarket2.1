package com.nilesh.moneymarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsFragment extends Fragment {

    private String stockSymbol = "ABC"; // Replace with the actual stock symbol
    private TextView symbolTextView, identifierTextView, currentValueTextView,
            previousClosedTextView, profitLossTextView, bodyTextView;
    private ImageView arrowIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        symbolTextView = rootView.findViewById(R.id.symbolTextView);
        identifierTextView = rootView.findViewById(R.id.identifierTextView);
        currentValueTextView = rootView.findViewById(R.id.currentValueTextView);
        previousClosedTextView = rootView.findViewById(R.id.previousClosedTextView);
        profitLossTextView = rootView.findViewById(R.id.profitLossTextView);
        arrowIcon = rootView.findViewById(R.id.arrowIcon);
        bodyTextView = rootView.findViewById(R.id.bodyTextView);

        fetchStockData();

        return rootView;
    }

    private void fetchStockData() {
        String url = "YOUR_STOCK_DATA_API_URL" + stockSymbol; // Replace with the actual API URL

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String stockIdentifier = response.getString("identifier");
                            double currentValue = response.getDouble("currentValue");
                            double previousClosed = response.getDouble("previousClosed");
                            double profitLoss = currentValue - previousClosed;
                            String body = response.getString("body");

                            symbolTextView.setText(getString(R.string.stock_symbol, stockSymbol));
                            identifierTextView.setText(getString(R.string.identifier, stockIdentifier));
                            currentValueTextView.setText(getString(R.string.current_value, currentValue));
                            previousClosedTextView.setText(getString(R.string.previous_closed, previousClosed));
                            profitLossTextView.setText(getString(R.string.profit_loss, profitLoss));

                            if (profitLoss >= 0) {
                                arrowIcon.setImageResource(R.drawable.ic_arrow_up);
                            } else {
                                arrowIcon.setImageResource(R.drawable.ic_arrow_down);
                            }

                            bodyTextView.setText(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(request);
    }
}
