package com.veronique.app.bitcoin.domain.ticker;

import com.veronique.app.bitcoin.domain.BaseDO;
import com.veronique.app.bitcoin.utils.MathUtils;

public class Ticker extends BaseDO {


    // 买一价
    private TickerAmount buy;

    // 卖一价
    private TickerAmount sell;

    // 最近一次成交价
    private TickerAmount last;


    public void setBuy(String buy) {
        this.buy = TickerAmount.of(buy);
    }

    public void setSell(String sell) {
        this.sell = TickerAmount.of(sell);
    }

    public void setLast(String last) {
        this.last = TickerAmount.of(last);
    }

    public TickerAmount getBuy() {
        return buy;
    }

    public TickerAmount getSell() {
        return sell;
    }

    public TickerAmount getLast() {
        return last;
    }

    public boolean isValid() {
        return MathUtils.isValid(last)
                && MathUtils.isValid(sell)
                && MathUtils.isValid(buy);
    }

    public void calculateDiff(Ticker ticker) {
        buy.calculateDiff(ticker.getBuy());
        sell.calculateDiff(ticker.getSell());
        last.calculateDiff(ticker.getLast());
    }
}
