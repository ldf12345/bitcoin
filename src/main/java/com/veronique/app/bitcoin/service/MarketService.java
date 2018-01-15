package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService extends BaseService {

    private final static String FILE_NAME = "market.json";

    private List<MarketDO> list = new ArrayList<>();

    private MarketDO defaultMarket;

    public MarketDO getByCode(String marketCode) {
        MarketDO currentMarketDO = null;
        for (MarketDO marketDO : list) {
            if (StringUtils.equalsIgnoreCase(marketCode, marketDO.getCode())) {
                currentMarketDO = marketDO;
                break;
            }
            if (marketDO.isDefaultMarket()) {
                defaultMarket = marketDO;
            }
        }
        return currentMarketDO == null ? defaultMarket : currentMarketDO;
    }

    public List<MarketDO> getList() {
        if (CollectionUtils.isEmpty(list)) {
            list = FileUtils.parseArray(FILE_NAME, MarketDO.class);
        }
        return list;
    }

    @Override
    public void refresh() {
        getList();
    }


}
