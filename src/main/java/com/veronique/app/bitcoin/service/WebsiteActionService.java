package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.domain.WebsiteActionDO;
import com.veronique.app.bitcoin.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebsiteActionService extends BaseService {

    private static final String FILE_NAME = "website_action.json";

    private List<WebsiteActionDO> list = new ArrayList<>();

    private Map<String, WebsiteActionDO> map = new HashMap<>();

    public List<WebsiteActionDO> getList() {
        if (CollectionUtils.isEmpty(list)) {
            list = FileUtils.parseArray(FILE_NAME, WebsiteActionDO.class);
        }
        return list;
    }

    private Map<String, WebsiteActionDO> getMap() {
        if (!CollectionUtils.isEmpty(list)) {
            for (WebsiteActionDO actionDO : list){
                map.put(actionDO.getWebsiteCode() + "_" + actionDO.getAction(), actionDO);
            }
        }
        return map;
    }

    public WebsiteActionDO getByCode(String websiteCode, String action) {
        return getMap().get(websiteCode + "_" + action);
    }

    @Override
    public void refresh() {
        getList();
    }

}
