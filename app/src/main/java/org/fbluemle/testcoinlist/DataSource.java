package org.fbluemle.testcoinlist;

import org.fbluemle.testcoinlist.data.TickerRow;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DataSource {
    private static final String URL = "https://api.coinmarketcap.com/v2/ticker/";
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static Call callTicker() {
        Request request = new Request.Builder()
                .url(URL)
                .build();

        return CLIENT.newCall(request);
    }

    public static TickerRow[] getTickerItems() {
        return new TickerRow[] {
                new TickerRow(1, "Bitcoin", getRandomPrice()),
                new TickerRow(2, "SP Coin", getRandomPrice()),
                new TickerRow(3, "Infinity", getRandomPrice()),
                new TickerRow(4, "Jarvis", getRandomPrice()),
                new TickerRow(5, "Bolt", getRandomPrice()),
        };
    }

    private static float getRandomPrice() {
        return (float) (Math.random() * 1000 + 1000);
    }
}
