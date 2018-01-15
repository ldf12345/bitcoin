package com.veronique.app.bitcoin.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService implements InitializingBean {


    public abstract void refresh();

    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }
}
