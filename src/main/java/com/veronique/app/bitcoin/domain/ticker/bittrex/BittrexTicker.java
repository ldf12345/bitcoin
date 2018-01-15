package com.veronique.app.bitcoin.domain.ticker.bittrex;

import com.veronique.app.bitcoin.domain.ticker.BaseTicker;
import com.veronique.app.bitcoin.domain.ticker.Ticker;

public class BittrexTicker extends BaseTicker {

    private boolean success;

    private String message;

    private BittrexTickerSub result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BittrexTickerSub getResult() {
        return result;
    }

    public void setResult(BittrexTickerSub result) {
        this.result = result;
    }

    @Override
    public Ticker toTicker() {
        if (result == null) {
            return new Ticker();
        }
        Ticker ticker = new Ticker();
        ticker.setBuy(this.result.getBid());
        ticker.setLast(this.result.getLast());
        ticker.setSell(this.result.getAsk());
        return ticker;
    }
}
