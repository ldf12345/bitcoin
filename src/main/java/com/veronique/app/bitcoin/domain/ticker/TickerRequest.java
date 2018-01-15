package com.veronique.app.bitcoin.domain.ticker;

import org.apache.commons.lang3.StringUtils;

public class TickerRequest<T> {

    private String market;

    private String currency;

    private String baseUrl;

    private String actionUrl;

    private String responseClass;

    public static TickerRequest of(String market, String currency, String baseUrl, String actionUrl, String responseClass) {
        if (StringUtils.equalsIgnoreCase(currency, "NA")) {
            return null;
        }
        TickerRequest request = new TickerRequest();
        request.market = market;
        request.currency = currency;
        request.baseUrl = baseUrl;
        request.actionUrl = actionUrl;
        request.responseClass = responseClass;
        return request;
    }

    public String getUrl() {
        StringBuilder sb = new StringBuilder(baseUrl);
        actionUrl = StringUtils.replace(actionUrl, "<currency>", StringUtils.lowerCase(currency));
        actionUrl = StringUtils.replace(actionUrl, "<market>", StringUtils.lowerCase(market));
        sb.append(actionUrl);
        return sb.toString();
    }

    public Class<T> getClazz() {
        try {
            return (Class<T>) Class.forName(responseClass);
        } catch (Exception e) {

        }

        return null;
    }

}
