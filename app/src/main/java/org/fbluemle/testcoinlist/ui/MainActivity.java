package org.fbluemle.testcoinlist.ui;

import org.fbluemle.testcoinlist.DataSource;
import org.fbluemle.testcoinlist.R;
import org.fbluemle.testcoinlist.api.TickerItem;
import org.fbluemle.testcoinlist.api.TickerQuote;
import org.fbluemle.testcoinlist.api.TickerResponse;
import org.fbluemle.testcoinlist.data.TickerRow;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TickerAdapter mTickerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTickerAdapter = new TickerAdapter();

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshTickerItems);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mTickerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        refreshTickerItems();
    }

    private void refreshTickerItems() {
        Log.d("MainActivity", "Refreshing ...");

        final Gson gson = new Gson();

        mSwipeRefreshLayout.setRefreshing(true);
        DataSource.callTicker().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", "Error", e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                TickerResponse tickerResponse =
                        gson.fromJson(response.body().charStream(), TickerResponse.class);
                final List<TickerRow> tickerRows = new ArrayList<>(tickerResponse.data.size());
                for (Map.Entry<String, TickerItem> item : tickerResponse.data.entrySet()) {
                    tickerRows.add(convert(item.getValue()));
                }
                Log.d("MainActivity", "Received ticker rows: " + tickerRows.size());
                runOnUiThread(() -> {
                    mTickerAdapter.setItems(tickerRows);
                    mSwipeRefreshLayout.setRefreshing(false);
                });
            }
        });
    }

    private TickerRow convert(TickerItem item) {
        TickerQuote usdQuote = item.quotes != null ? item.quotes.get("USD") : null;
        TickerRow tickerRow = new TickerRow();
        tickerRow.rank = item.rank;
        tickerRow.name = item.name;
        tickerRow.price = usdQuote != null ? usdQuote.price : 0;
        return tickerRow;
    }
}
