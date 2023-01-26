package com.upuphone.cloudplatform.demo.business.service;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/2/8 15:25
 */
public interface CatService {

    String getUserName(Long id);

    String manualTransaction(String param);

    String automaticTransaction(String param);

    String trace(String param);

    String metric(String param);

    void logError();
}
