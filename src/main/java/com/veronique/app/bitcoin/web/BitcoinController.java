package com.veronique.app.bitcoin.web;

import com.veronique.app.bitcoin.domain.MarketDO;
import com.veronique.app.bitcoin.domain.WebsiteDO;
import com.veronique.app.bitcoin.dto.TickerRequestDTO;
import com.veronique.app.bitcoin.dto.TickerResponseDTO;
import com.veronique.app.bitcoin.service.MarketService;
import com.veronique.app.bitcoin.service.TickerService;
import com.veronique.app.bitcoin.service.WebsiteService;
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


    @RequestMapping("ticker")
    public String getTickerV2(String market, Map<String, Object> model) {
        try {
            model.put("markets", marketService.getList());
            MarketDO marketDO = marketService.getByCode(market);

            List<WebsiteDO> websites = websiteService.getList();
            model.put("websites", websites);


            TickerRequestDTO requestDTO = new TickerRequestDTO(marketDO, websites);
            TickerResponseDTO responseDTO = tickerService.getTickerGroupsAsync(requestDTO);

            model.put("tickerGroups", responseDTO.getTickerGroupList());

            model.put("currentMarket", StringUtils.upperCase(marketDO.getCode()));

        } catch (Exception e) {
            model.put("msg", "系统异常，请稍后重试");
        }

        return "ticker";
    }

}
