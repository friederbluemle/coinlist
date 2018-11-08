package org.fbluemle.testcoinlist.api;

import java.util.Map;

public class TickerItem {
    public int id;
    public String name;
    public String symbol;
    public int rank;
    public Map<String, TickerQuote> quotes;
}

/*
{
            "id": 1,
            "name": "Bitcoin",
            "symbol": "BTC",
            "website_slug": "bitcoin",
            "rank": 1,
            "circulating_supply": 17008162.0,
            "total_supply": 17008162.0,
            "max_supply": 21000000.0,
            "quotes": {
                "USD": {
                    "price": 9024.09,
                    "volume_24h": 8765400000.0,
                    "market_cap": 153483184623.0,
                    "percent_change_1h": -2.31,
                    "percent_change_24h": -4.18,
                    "percent_change_7d": -0.47
                }
            },
            "last_updated": 1525137271
        }
 */
