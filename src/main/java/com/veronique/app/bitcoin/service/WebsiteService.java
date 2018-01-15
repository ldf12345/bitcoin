package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteService extends BaseService {

    private final static String FILE_NAME = "website.json";

    private List<WebsiteDO> list = new ArrayList<>();

    public List<WebsiteDO> getList() {
        if (CollectionUtils.isEmpty(list)) {
            list = FileUtils.parseArray(FILE_NAME, WebsiteDO.class);
        }
        return list;
    }

    @Override
    public void refresh() {
        getList();
    }
}
