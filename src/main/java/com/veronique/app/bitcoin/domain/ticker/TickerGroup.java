package com.veronique.app.bitcoin.domain.ticker;

import com.veronique.app.bitcoin.domain.CurrencyDO;

import java.util.ArrayList;
import java.util.List;

public class TickerGroup extends BaseTicker {

    // 币种
    private CurrencyDO currency;

    // 行情
    private List<Ticker> tickers = new ArrayList<>();

    public CurrencyDO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDO currency) {
        this.currency = currency;
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }
}
