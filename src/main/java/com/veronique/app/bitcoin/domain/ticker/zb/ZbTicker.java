package com.veronique.app.bitcoin.domain.ticker.zb;

import com.veronique.app.bitcoin.domain.ticker.BaseTicker;
import com.veronique.app.bitcoin.domain.ticker.Ticker;

public class ZbTicker extends BaseTicker {

    private ZbTickerSub ticker;

    private long date;

    public ZbTickerSub getTicker() {
        return ticker;
    }

    public void setTicker(ZbTickerSub ticker) {
        this.ticker = ticker;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public Ticker toTicker() {
        Ticker ticker = new Ticker();
        if (this.ticker != null) {
            ticker = this.ticker.toTicker();
        }
        return ticker;
    }
}
