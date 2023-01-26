package com.upuphone.cloudplatform.demo.business.manager;

import com.upuphone.cloudplatform.demo.api.dto.request.DemoRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.DemoResponse;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/28 17:49
 */
public interface DemoManager {

    DemoResponse doSomething(DemoRequest demoRequest);
}
