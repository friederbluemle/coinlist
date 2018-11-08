package org.fbluemle.testcoinlist.ui;

import org.fbluemle.testcoinlist.DataSource;
import org.fbluemle.testcoinlist.R;
import org.fbluemle.testcoinlist.data.TickerRow;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;

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

        mSwipeRefreshLayout.setRefreshing(true);
        TickerRow[] items = DataSource.getTickerItems();
        mTickerAdapter.setItems(Arrays.asList(items));
        mSwipeRefreshLayout.setRefreshing(false);

        DataSource.callTicker().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", "Error", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("MainActivity", "From API: " + response.body().string());
            }
        });
    }
}
