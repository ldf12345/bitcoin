package com.veronique.app.bitcoin.domain;

import com.veronique.app.bitcoin.domain.ticker.BaseTicker;

import java.util.ArrayList;
import java.util.List;

public class CurrencyGroupDO extends BaseTicker {

    // 币种
    private CurrencyDO currency;

    // 币种关联
    private List<WebsiteCurrencyDO> websiteCurrencies = new ArrayList<>();

    public CurrencyDO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDO currency) {
        this.currency = currency;
    }

    public List<WebsiteCurrencyDO> getWebsiteCurrencies() {
        return websiteCurrencies;
    }

    public void setWebsiteCurrencies(List<WebsiteCurrencyDO> websiteCurrencies) {
        this.websiteCurrencies = websiteCurrencies;
    }
}
