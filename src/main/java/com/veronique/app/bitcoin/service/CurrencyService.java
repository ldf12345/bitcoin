package com.veronique.app.bitcoin.service;

import com.alibaba.fastjson.JSON;
import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CurrencyService extends BaseService {

    private final static String FILE_NAME = "currency.json";

    private List<CurrencyDO> list = new ArrayList<>();

    public List<CurrencyDO> getList() {
        if (CollectionUtils.isEmpty(list)) {
            list = FileUtils.parseArray(FILE_NAME, CurrencyDO.class);
        }
        return list;
    }

    public void save(CurrencyDO newCurrencyDO) {
        newCurrencyDO.setCode(newCurrencyDO.getCode().toUpperCase());
        Iterator<CurrencyDO> iterator = list.iterator();
        while (iterator.hasNext()) {
            CurrencyDO currencyDO = iterator.next();
            if (StringUtils.equalsIgnoreCase(newCurrencyDO.getCode(), currencyDO.getCode())) {
                iterator.remove();
            }
        }
        list.add(0, newCurrencyDO);

        FileUtils.writeFile(FILE_NAME, JSON.toJSONString(list, true));
    }

    public void delete(CurrencyDO newCurrencyDO) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        newCurrencyDO.setCode(newCurrencyDO.getCode().toUpperCase());
        Iterator<CurrencyDO> iterator = list.iterator();
        while (iterator.hasNext()) {
            CurrencyDO currencyDO = iterator.next();
            if (StringUtils.equalsIgnoreCase(newCurrencyDO.getCode(), currencyDO.getCode())) {
                iterator.remove();
            }
        }

        FileUtils.writeFile(FILE_NAME, JSON.toJSONString(list, true));
    }

    @Override
    public void refresh() {
        getList();
    }
}
