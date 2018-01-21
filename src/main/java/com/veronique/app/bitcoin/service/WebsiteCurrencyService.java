package com.veronique.app.bitcoin.service;

import com.alibaba.fastjson.JSON;
import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.domain.CurrencyGroupDO;
import com.veronique.app.bitcoin.domain.WebsiteCurrencyDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.dto.CurrencyRequestDTO;
import com.veronique.app.bitcoin.dto.CurrencyResponseDTO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            groups.add(getCurrencyGroup(new CurrencyDO(), requestDTO.getWebsiteList()));
            for (CurrencyDO currencyDO : requestDTO.getCurrencyList()) {
                groups.add(getCurrencyGroup(currencyDO, requestDTO.getWebsiteList()));
            }

        } catch (Exception e) {

        }

        return new CurrencyResponseDTO(groups);
    }

    public CurrencyGroupDO getCurrencyGroup(CurrencyDO currencyDO, List<WebsiteDO> websiteList) {
        CurrencyGroupDO groupDO = new CurrencyGroupDO();
        groupDO.setCurrency(currencyDO);
        for (WebsiteDO websiteDO : websiteList) {
            groupDO.getWebsiteCurrencies().add(getByCode(websiteDO.getCode(), currencyDO.getCode()));
        }
        return  groupDO;
    }


    public void save(CurrencyGroupDO currencyGroupDO) {
        if (!CollectionUtils.isEmpty(currencyGroupDO.getWebsiteCurrencies())) {
            CurrencyDO currencyDO = currencyGroupDO.getCurrency();
            for (WebsiteCurrencyDO newWebsiteCurrencyDO : currencyGroupDO.getWebsiteCurrencies()) {
                String key = newWebsiteCurrencyDO.getWebsiteCode() + "_" + currencyDO.getCode();
                WebsiteCurrencyDO websiteCurrencyDO = getMap().get(key);
                if (websiteCurrencyDO != null) {
                    if (StringUtils.isBlank(newWebsiteCurrencyDO.getCustomCurrency())) {
                        websiteCurrencyDO.setCustomCurrency("");
                    } else {
                        websiteCurrencyDO.setCustomCurrency(newWebsiteCurrencyDO.getCustomCurrency());
                    }
                } else {
                    if (!StringUtils.isBlank(newWebsiteCurrencyDO.getCustomCurrency())) {
                        newWebsiteCurrencyDO.setCurrencyCode(currencyDO.getCode());
                        list.add(0, newWebsiteCurrencyDO);
                        map.put(key, newWebsiteCurrencyDO);
                    }
                }
            }

            FileUtils.writeFile(FILE_NAME, JSON.toJSONString(list, true));
        }
    }

    @Override
    public void refresh() {
        getList();
    }


}
