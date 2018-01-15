package com.veronique.app.bitcoin.domain.ticker;

import java.util.ArrayList;
import java.util.List;

public class TickerGroup extends BaseTicker {

    // 币种
    private String currency;

    // 行情
    private List<Ticker> tickers = new ArrayList<>();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }
}
