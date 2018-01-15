package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    @Override
    public void refresh() {
        getList();
    }
}
