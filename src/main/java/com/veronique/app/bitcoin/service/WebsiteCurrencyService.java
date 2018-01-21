package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.domain.CurrencyGroupDO;
import com.veronique.app.bitcoin.domain.WebsiteCurrencyDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.domain.ticker.TickerGroup;
import com.veronique.app.bitcoin.dto.CurrencyRequestDTO;
import com.veronique.app.bitcoin.dto.CurrencyResponseDTO;
import com.veronique.app.bitcoin.dto.TickerRequestDTO;
import com.veronique.app.bitcoin.dto.TickerResponseDTO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class WebsiteCurrencyService extends BaseService {

    private static final String FILE_NAME = "website_currency.json";

    private List<WebsiteCurrencyDO> list = new ArrayList<>();

    private Map<String, WebsiteCurrencyDO> map = new HashMap<>();

    public List<WebsiteCurrencyDO> getList() {
        if (CollectionUtils.isEmpty(list)) {
            list = FileUtils.parseArray(FILE_NAME, WebsiteCurrencyDO.class);
        }
        return list;
    }

    private Map<String, WebsiteCurrencyDO> getMap() {
        if (!CollectionUtils.isEmpty(list)) {
            for (WebsiteCurrencyDO currencyDO : list){
                map.put(currencyDO.getWebsiteCode() + "_"+ currencyDO.getCurrencyCode(), currencyDO);
            }
        }
        return map;
    }

    public WebsiteCurrencyDO getByCode(String websiteCode, String currencyCode) {
        WebsiteCurrencyDO currencyDO = getMap().get(websiteCode + "_" + currencyCode);
        if (currencyDO == null) {
            currencyDO = new WebsiteCurrencyDO();
            currencyDO.setWebsiteCode(websiteCode);
            currencyDO.setCurrencyCode(currencyCode);
        }
        return currencyDO;
    }

    public CurrencyResponseDTO geCurrencyGroups(CurrencyRequestDTO requestDTO) {
        List<CurrencyGroupDO> groups = new ArrayList<>(requestDTO.getCurrencyList().size());
        try {
            for (CurrencyDO currencyDO : requestDTO.getCurrencyList()) {
                CurrencyGroupDO groupDO = new CurrencyGroupDO();
                groupDO.setCurrency(currencyDO);
                for (WebsiteDO websiteDO : requestDTO.getWebsiteList()) {
                    groupDO.getWebsiteCurrencies().add(getByCode(websiteDO.getCode(), currencyDO.getCode()));
                }
                groups.add(groupDO);
            }

        } catch (Exception e) {

        }

        return new CurrencyResponseDTO(groups);
    }

    @Override
    public void refresh() {
        getList();
    }


}
