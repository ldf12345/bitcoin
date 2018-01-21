package com.veronique.app.bitcoin.dto;

import com.veronique.app.bitcoin.domain.BaseDO;
import com.veronique.app.bitcoin.domain.CurrencyGroupDO;
import com.veronique.app.bitcoin.domain.ticker.TickerGroup;

import java.util.List;

public class CurrencyResponseDTO extends BaseDO {

    private List<CurrencyGroupDO> currencyGroupList;

    public CurrencyResponseDTO() {
    }

    public CurrencyResponseDTO(List<CurrencyGroupDO> currencyGroupList) {
        this.currencyGroupList = currencyGroupList;
    }

    public List<CurrencyGroupDO> getCurrencyGroupList() {
        return currencyGroupList;
    }

    public void setCurrencyGroupList(List<CurrencyGroupDO> currencyGroupList) {
        this.currencyGroupList = currencyGroupList;
    }
}
