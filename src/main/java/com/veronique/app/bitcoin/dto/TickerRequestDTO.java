package com.veronique.app.bitcoin.dto;

import com.veronique.app.bitcoin.domain.BaseDO;
import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;

import java.util.List;

public class TickerRequestDTO extends BaseDO {

    private MarketDO marketDO;

    private List<WebsiteDO> websiteList;

    public TickerRequestDTO() {
    }

    public TickerRequestDTO(MarketDO marketDO,  List<WebsiteDO> websiteList) {
        this.marketDO = marketDO;
        this.websiteList = websiteList;
    }

    public MarketDO getMarketDO() {
        return marketDO;
    }

    public void setMarketDO(MarketDO marketDO) {
        this.marketDO = marketDO;
    }

    public List<WebsiteDO> getWebsiteList() {
        return websiteList;
    }

    public void setWebsiteList(List<WebsiteDO> websiteList) {
        this.websiteList = websiteList;
    }
}
