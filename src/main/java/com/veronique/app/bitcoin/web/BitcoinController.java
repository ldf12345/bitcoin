package com.veronique.app.bitcoin.web;

import com.veronique.app.bitcoin.domain.CurrencyDO;
import com.veronique.app.bitcoin.domain.CurrencyGroupDO;
import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.dto.CurrencyRequestDTO;
import com.veronique.app.bitcoin.dto.CurrencyResponseDTO;
import com.veronique.app.bitcoin.dto.TickerRequestDTO;
import com.veronique.app.bitcoin.dto.TickerResponseDTO;
import com.veronique.app.bitcoin.dto.ajax.AjaxResponseDTO;
import com.veronique.app.bitcoin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private WebsiteCurrencyService websiteCurrencyService;


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

            List<CurrencyDO> currencyList = currencyService.getList();
            CurrencyRequestDTO requestDTO = new CurrencyRequestDTO(currencyList, websites);
            CurrencyResponseDTO responseDTO = websiteCurrencyService.geCurrencyGroups(requestDTO);
            model.put("currencyGroups", responseDTO.getCurrencyGroupList());

        } catch (Exception e) {
            model.put("msg", "系统异常，请稍后重试");
        }

        return "currency";
    }

    @RequestMapping(value = "currency/save")
    public @ResponseBody AjaxResponseDTO saveCurrency(@RequestBody CurrencyGroupDO currencyGroupDO) {
        AjaxResponseDTO responseDTO = new AjaxResponseDTO();
        try {
            if (currencyGroupDO.getCurrency() == null || StringUtils.isBlank(currencyGroupDO.getCurrency().getCode())) {
                responseDTO.setMsg("请输入币种信息");
                return responseDTO;
            }
            currencyService.save(currencyGroupDO.getCurrency());
            websiteCurrencyService.save(currencyGroupDO);
            responseDTO.setSuccess(true);
        } catch (Exception e ) {
            responseDTO.setMsg("怎么就出现错误了呢");
        }

        return responseDTO;
    }


    @RequestMapping(value = "currency/delete")
    public @ResponseBody AjaxResponseDTO deleteCurrency(@RequestBody CurrencyGroupDO currencyGroupDO) {
        AjaxResponseDTO responseDTO = new AjaxResponseDTO();
        try {
            if (currencyGroupDO.getCurrency() == null || StringUtils.isBlank(currencyGroupDO.getCurrency().getCode())) {
                responseDTO.setMsg("请输入币种信息");
                return responseDTO;
            }
            currencyService.delete(currencyGroupDO.getCurrency());
            responseDTO.setSuccess(true);
        } catch (Exception e ) {
            responseDTO.setMsg("怎么就出现错误了呢");
        }
        return responseDTO;
    }

}
