package com.veronique.app.bitcoin.domain.ticker.bittrex;

public class BittrexTickerSub {

    //买方价
    private String Ask;

    //买方价
    private String Bid;

    //最新价
    private String Last;

    public String getAsk() {
        return Ask;
    }

    public void setAsk(String ask) {
        Ask = ask;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }
}
