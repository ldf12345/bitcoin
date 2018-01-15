package com.veronique.app.bitcoin.domain;

public class WebsiteActionDO extends BaseDO {

    private String websiteCode;

    private String action;

    private String actionUrlPattern;

    private String responseClass;

    public String getWebsiteCode() {
        return websiteCode;
    }

    public void setWebsiteCode(String websiteCode) {
        this.websiteCode = websiteCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionUrlPattern() {
        return actionUrlPattern;
    }

    public void setActionUrlPattern(String actionUrlPattern) {
        this.actionUrlPattern = actionUrlPattern;
    }

    public String getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(String responseClass) {
        this.responseClass = responseClass;
    }
}
