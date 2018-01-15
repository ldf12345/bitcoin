package com.veronique.app.bitcoin.dto;

import com.veronique.app.bitcoin.domain.BaseDO;
import com.veronique.app.bitcoin.domain.ticker.TickerGroup;

import java.util.List;

public class TickerResponseDTO extends BaseDO {

    private List<TickerGroup> tickerGroupList;

    public TickerResponseDTO() {
    }

    public TickerResponseDTO(List<TickerGroup> tickerGroupList) {
        this.tickerGroupList = tickerGroupList;
    }

    public List<TickerGroup> getTickerGroupList() {
        return tickerGroupList;
    }

    public void setTickerGroupList(List<TickerGroup> tickerGroupList) {
        this.tickerGroupList = tickerGroupList;
    }
}
