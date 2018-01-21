package com.veronique.app.bitcoin.dto;

import com.veronique.app.bitcoin.domain.BaseDO;
import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;

import java.util.List;

public class CurrencyRequestDTO extends BaseDO {

    private List<CurrencyDO> currencyList;

    private List<WebsiteDO> websiteList;

    public CurrencyRequestDTO() {
    }

    public CurrencyRequestDTO(List<CurrencyDO> currencyList, List<WebsiteDO> websiteList) {
        this.currencyList = currencyList;
        this.websiteList = websiteList;
    }

    public List<CurrencyDO> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyDO> currencyList) {
        this.currencyList = currencyList;
    }

    public List<WebsiteDO> getWebsiteList() {
        return websiteList;
    }

    public void setWebsiteList(List<WebsiteDO> websiteList) {
        this.websiteList = websiteList;
    }
}
