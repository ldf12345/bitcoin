package com.veronique.app.bitcoin.utils;

import com.veronique.app.bitcoin.domain.ticker.TickerAmount;
import org.apache.commons.lang3.StringUtils;

public class MathUtils {

    public static boolean isValid(TickerAmount amount) {
        if (amount != null && StringUtils.isNotBlank(amount.getAmount())) {
            return true;
        }
        return false;
    }

}
