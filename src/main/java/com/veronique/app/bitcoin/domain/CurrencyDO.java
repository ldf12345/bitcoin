package com.veronique.app.bitcoin.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class CurrencyDO extends BaseDO {

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JSONField(serialize = false)
    public String getDisplayName() {
        return this.getCode() + "(" + this.getDesc() + ")";
    }
}
