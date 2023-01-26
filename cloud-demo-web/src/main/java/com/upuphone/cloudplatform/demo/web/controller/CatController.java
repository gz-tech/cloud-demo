package com.upuphone.cloudplatform.demo.web.controller;

import com.upuphone.cloudplatform.demo.api.dto.request.CatRequest;
import com.upuphone.cloudplatform.demo.business.service.CatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/2/8 15:06
 */
@Slf4j
@RestController
@Api(value = "cat示例", tags = "cat示例")
@RequestMapping(value = "/cat")
public class CatController {

    @Resource
    private CatService catService;

    @GetMapping("/alert")
    @ApiOperation(value = "测试告警接收")
    public String alert(@RequestParam("key") String key, @RequestParam("value") String value) {
        log.info("key: {}, value: {}", key, value);
        return "success";
    }

    @GetMapping("/event")
    @ApiOperation(value = "event-类型示例")
    public String event(@RequestParam("id") Long id) {
        return catService.getUserName(id);
    }

    @GetMapping("/transaction/manual")
    @ApiOperation(value = "transaction-类型手动埋点示例")
    public String manualTransaction(@RequestParam("param") String param) {
        log.info("manualTransaction, {}", param);
        return catService.manualTransaction(param);
    }

    @PostMapping("/transaction/automatic")
    @ApiOperation(value = "transaction-类型自动埋点示例")
    public String automaticTransaction(@RequestBody CatRequest catRequest) {
        log.info("automaticTransaction, {}", catRequest.toString());
        return catService.automaticTransaction(catRequest.getStrParam());
    }

    @GetMapping("/trace")
    @ApiOperation(value = "链路测试")
    public String trace(@RequestParam("param") String param) {
        return catService.trace(param);
    }

    @GetMapping("/metric")
    @ApiOperation(value = "metric-业务指标示例")
    public String metric(@RequestParam("param") String param) {
        return catService.metric(param);
    }

    @GetMapping("/transaction/url/{id}")
    @ApiOperation(value = "url统计测试-get请求")
    public String getUrl(@PathVariable("id") Long id) {
        log.info("getUrl, {}", id);
        return "ok";
    }
    @DeleteMapping("/transaction/url/{id}")
    @ApiOperation(value = "url统计测试-delete请求")
    public String deleteUrl(@PathVariable("id") Long id) {
        log.info("deleteUrl, {}", id);
        return "ok";
    }
    @GetMapping("/transaction/url/str/{param}")
    @ApiOperation(value = "url统计测试-get请求,字符占位符")
    public String getUrlByParamString(@PathVariable("param") String param) {
        log.info("getUrlByParamString, {}", param);
        return "ok";
    }
    @PostMapping("/transaction/url")
    @ApiOperation(value = "url统计测试-post请求")
    public String postUrl(@RequestBody CatRequest catRequest) {
        log.info("postUrl, {}", catRequest.toString());
        return "ok";
    }

    @GetMapping("/log/error")
    @ApiOperation(value = "错误日志示例")
    public void logError() {
        catService.logError();
    }



}
