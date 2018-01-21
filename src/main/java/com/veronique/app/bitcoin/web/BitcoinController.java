package com.veronique.app.bitcoin.web;

import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.dto.CurrencyRequestDTO;
import com.veronique.app.bitcoin.dto.CurrencyResponseDTO;
import com.veronique.app.bitcoin.dto.TickerRequestDTO;
import com.veronique.app.bitcoin.dto.TickerResponseDTO;
import com.veronique.app.bitcoin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bitcoin")
public class BitcoinController {

    @Resource
    private MarketService marketService;

    @Resource
    private WebsiteService websiteService;

    @Resource
    private TickerService tickerService;

    @Resource
    private CurrencyService currencyService;

    @Resource
    private WebsiteCurrencyService websiteCurrencyServicee;


    @RequestMapping("ticker")
    public String getTicker(String market, Map<String, Object> model) {
        try {
            model.put("markets", marketService.getList());
            MarketDO marketDO = marketService.getByCode(market);

            List<WebsiteDO> websites = websiteService.getList();
            model.put("websites", websites);


            TickerRequestDTO requestDTO = new TickerRequestDTO(marketDO, currencyService.getList(), websites);
            TickerResponseDTO responseDTO = tickerService.getTickerGroupsAsync(requestDTO);

            model.put("tickerGroups", responseDTO.getTickerGroupList());

            model.put("currentMarket", StringUtils.upperCase(marketDO.getCode()));

        } catch (Exception e) {
            model.put("msg", "系统异常，请稍后重试");
        }

        return "ticker";
    }


    @RequestMapping("currency")
    public String getCurrency(Map<String, Object> model) {
        try {

            List<WebsiteDO> websites = websiteService.getList();
            model.put("websites", websites);

            CurrencyRequestDTO requestDTO = new CurrencyRequestDTO(currencyService.getList(), websites);
            CurrencyResponseDTO responseDTO = websiteCurrencyServicee.geCurrencyGroups(requestDTO);

            model.put("currencyGroups", responseDTO.getCurrencyGroupList());

        } catch (Exception e) {
            model.put("msg", "系统异常，请稍后重试");
        }

        return "currency";
    }

}
