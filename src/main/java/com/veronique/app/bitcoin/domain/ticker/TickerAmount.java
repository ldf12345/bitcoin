package com.veronique.app.bitcoin.domain.ticker;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class TickerAmount {

    // 买一价
    public String amount;

    // 卖一价
    public String perchange;

    // 最近一次成交价
    public int level;

    public String getAmount() {
        return amount == null ? StringUtils.EMPTY : StringUtils.substring(amount, 0, 7);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPerchange() {
        return perchange == null ? StringUtils.EMPTY : StringUtils.substring(perchange, 0, 6);
    }

    public void setPerchange(String perchange) {
        this.perchange = perchange;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static TickerAmount of (String amount) {
        TickerAmount tickerAmount = new TickerAmount();
        tickerAmount.setAmount(amount);
        return tickerAmount;
    }

    public void calculateDiff(TickerAmount amount) {
        BigDecimal value2 = new BigDecimal(this.amount);
        BigDecimal value1 = new BigDecimal(amount.amount);
        BigDecimal perchange =  (value2.subtract(value1)).divide(value1, 8, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        if (perchange.floatValue() > 5) {
            this.setLevel(2);
        } else
        if (perchange.floatValue() > 0) {
            this.setLevel(1);
        } else
        if (perchange.floatValue() < 0) {
            this.setLevel(-1);
        }

        this.setPerchange(perchange.toString());
    }
}
