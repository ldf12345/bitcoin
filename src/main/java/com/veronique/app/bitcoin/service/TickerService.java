package com.veronique.app.bitcoin.service;

import com.veronique.app.bitcoin.api.ticker.TickerApiExecutor;
import com.veronique.app.bitcoin.domain.*;
import com.veronique.app.bitcoin.domain.ticker.Ticker;
import com.veronique.app.bitcoin.domain.ticker.TickerGroup;
import com.veronique.app.bitcoin.domain.ticker.TickerRequest;
import com.veronique.app.bitcoin.dto.TickerRequestDTO;
import com.veronique.app.bitcoin.dto.TickerResponseDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TickerService {

    private static final String ACTION_CODE = "TICKER";

    @Resource
    private TickerApiExecutor executor;

    @Resource
    private WebsiteActionService websiteActionService;

    @Resource
    private WebsiteCurrencyService websiteCurrencyService;

    public TickerResponseDTO getTickerGroupsAsync(TickerRequestDTO requestDTO) {
        Long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(requestDTO.getCurrencyList().size());
        List<TickerGroup> groups = new ArrayList<>(requestDTO.getCurrencyList().size());
        List<Future<TickerGroup>> futureList = new ArrayList<Future<TickerGroup>>();
        try {

            for (CurrencyDO currencyDO : requestDTO.getCurrencyList()) {
                futureList.add(executorService.submit(new CallableTask(requestDTO.getMarketDO(), currencyDO, requestDTO.getWebsiteList())));
            }

            for (Future<TickerGroup> future : futureList) {
                //CPU高速轮询：每个future都并发轮循，判断完成状态然后获取结果，这一行，是本实现方案的精髓所在。即有10个future在高速轮询，完成一个future的获取结果，就关闭一个轮询
                while (true) {
                    //获取future成功完成状态，如果想要限制每个任务的超时时间，取消本行的状态判断+future.get(1000*1, TimeUnit.MILLISECONDS)+catch超时异常使用即可。
                    if (future.isDone() && !future.isCancelled()) {
                        TickerGroup tickerGroup = future.get();//获取结果
                        groups.add(tickerGroup);
                        //当前future获取结果完毕，跳出while
                        break;
                    } else {
                        //每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                        Thread.sleep(1);
                    }
                }
            }

        } catch (Exception e) {

        }

        executorService.shutdownNow();
        System.out.println("总耗时=" + (System.currentTimeMillis() - start));
        return new TickerResponseDTO(groups);
    }


    class CallableTask implements Callable<TickerGroup> {

        private MarketDO marketDO;

        private CurrencyDO currencyDO;

        private List<WebsiteDO> websiteList;

        public CallableTask(MarketDO marketDO, CurrencyDO currencyDO, List<WebsiteDO> websiteList) {
            this.marketDO = marketDO;
            this.currencyDO = currencyDO;
            this.websiteList = websiteList;

        }

        @Override
        public TickerGroup call() throws Exception {
            return getTickerGroup(marketDO, currencyDO, websiteList);
        }
    }

    public TickerGroup getTickerGroup(MarketDO marketDO, CurrencyDO currencyDO, List<WebsiteDO> websiteList) {
        TickerGroup tickerGroup = new TickerGroup();
        tickerGroup.setCurrency(currencyDO);
        try {
            Ticker standardTicker = null;
            for (WebsiteDO websiteDO : websiteList) {
                Ticker ticker = getTicker(marketDO, currencyDO, websiteDO);
                if (ticker.isValid()) {
                    if (standardTicker == null) {
                        standardTicker = ticker;
                    } else {
                        ticker.calculateDiff(standardTicker);
                    }
                }
                tickerGroup.getTickers().add(ticker);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickerGroup;
    }

    public Ticker getTicker(MarketDO marketDO, CurrencyDO currencyDO, WebsiteDO websiteDO) {
        Ticker ticker;
        try {
            WebsiteActionDO websiteActionDO = websiteActionService.getByCode(websiteDO.getCode(), ACTION_CODE);
            WebsiteCurrencyDO websiteCurrencyDO = websiteCurrencyService.getByCode(websiteDO.getCode(), currencyDO.getCode());
            TickerRequest request = TickerRequest.of(marketDO.getCode(), websiteCurrencyDO.getRealCurrency(), websiteDO.getBaseUrl(), websiteActionDO.getActionUrlPattern(), websiteActionDO.getResponseClass());
            ticker = executor.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            ticker = new Ticker();
        }
        return ticker;
    }


}
