package com.veronique.app.bitcoin.api.ticker;

import com.alibaba.fastjson.JSON;
import com.veronique.app.bitcoin.api.ApiExecutor;
import com.veronique.app.bitcoin.domain.ticker.BaseTicker;
import com.veronique.app.bitcoin.domain.ticker.Ticker;
import com.veronique.app.bitcoin.domain.ticker.TickerRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TickerApiExecutor extends ApiExecutor {

    public <T extends BaseTicker> Ticker execute(TickerRequest<T> request) {

        Ticker ticker = null;
        if (request == null) {
            ticker = new Ticker();
            return ticker;
        }
        try {
            String response = super.execute(request.getUrl());

            BaseTicker baseTicker = null;
            if (StringUtils.isNotBlank(response)) {
                baseTicker = JSON.parseObject(response, request.getClazz());
            }

            if (baseTicker != null) {
                ticker = baseTicker.toTicker();
            }

        } catch (Exception e) {
            ticker = new Ticker();
        }

        return ticker;
    }




}
