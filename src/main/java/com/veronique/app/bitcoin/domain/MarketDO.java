package com.veronique.app.bitcoin.domain;

public class MarketDO extends BaseDO {

    private String code;

    private String desc;

    private boolean defaultMarket;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDefaultMarket() {
        return defaultMarket;
    }

    public void setDefaultMarket(boolean defaultMarket) {
        this.defaultMarket = defaultMarket;
    }
}
