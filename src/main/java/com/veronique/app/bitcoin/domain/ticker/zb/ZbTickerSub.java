package com.veronique.app.bitcoin.domain.ticker.zb;

import com.veronique.app.bitcoin.domain.ticker.BaseTicker;
import com.veronique.app.bitcoin.domain.ticker.Ticker;

public class ZbTickerSub extends BaseTicker {

    // 最高价
    private String high;

    // 最低价
    private String low;

    // 买一价
    private String buy;

    // 卖一价
    private String sell;

    // 最近一次成交价
    private String last;

    // 成交量
    private String vol;

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    @Override
    public Ticker toTicker() {
        Ticker ticker = new Ticker();
        ticker.setBuy(this.getBuy());
        ticker.setLast(this.getLast());
        ticker.setSell(this.getSell());
        return ticker;
    }
}
