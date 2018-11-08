package org.fbluemle.testcoinlist;

import org.fbluemle.testcoinlist.data.TickerRow;

public class DataSource {
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
