package com.veronique.app.bitcoin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;

public class WebsiteCurrencyDO extends BaseDO {

    private String websiteCode;

    private String currencyCode;

    private String customCurrency;

    public String getWebsiteCode() {
        return websiteCode;
    }

    public void setWebsiteCode(String websiteCode) {
        this.websiteCode = websiteCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCustomCurrency() {
        return customCurrency;
    }

    public void setCustomCurrency(String customCurrency) {
        this.customCurrency = customCurrency;
    }

    @JSONField(serialize = false)
    public String getRealCurrency() {
        return StringUtils.isNotBlank(customCurrency) ?  customCurrency : currencyCode;
    }
}
