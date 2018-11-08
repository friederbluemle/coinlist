package org.fbluemle.testcoinlist.ui;

import org.fbluemle.testcoinlist.R;
import org.fbluemle.testcoinlist.data.TickerRow;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

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
        mSwipeRefreshLayout.setRefreshing(true);
        TickerRow[] items = getTickerItems();
        mTickerAdapter.setItems(Arrays.asList(items));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private TickerRow[] getTickerItems() {
        return new TickerRow[] {
                new TickerRow(1, "Bitcoin", getRandomPrice()),
                new TickerRow(2, "SP Coin", getRandomPrice()),
                new TickerRow(3, "Infinity", getRandomPrice()),
                new TickerRow(4, "Jarvis", getRandomPrice()),
                new TickerRow(5, "Bolt", getRandomPrice()),
        };
    }

    private float getRandomPrice() {
        return (float) (Math.random() * 1000 + 1000);
    }
}
