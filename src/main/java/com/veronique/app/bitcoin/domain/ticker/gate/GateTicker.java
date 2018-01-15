package com.veronique.app.bitcoin.domain.ticker.gate;

import com.veronique.app.bitcoin.domain.ticker.BaseTicker;
import com.veronique.app.bitcoin.domain.ticker.Ticker;

public class GateTicker extends BaseTicker {

    //
    private boolean result;

    // 交易量
    private String baseVolume;

    // 24小时最高价
    private String high24hr;

    // 买方最高价
    private String highestBid;

    // 最新成交价
    private String last;

    // 24小时最低价
    private String low24hr;

    // 卖方最低价
    private String lowestAsk;

    // 涨跌百分比
    private String percentChange;

    //兑换货币交易量
    private String quoteVolume;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(String high24hr) {
        this.high24hr = high24hr;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(String low24hr) {
        this.low24hr = low24hr;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public Ticker toTicker() {
        Ticker ticker = new Ticker();
        ticker.setBuy(this.highestBid);
        ticker.setLast(this.last);
        ticker.setSell(this.lowestAsk);
        return ticker;
    }
}
