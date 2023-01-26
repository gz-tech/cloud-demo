package com.upuphone.cloudplatform.demo.web.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.NamingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upuphone.cloudplatform.demo.api.dto.request.DemoRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.DemoResponse;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.business.manager.DemoManager;
import com.upuphone.cloudplatform.demo.business.service.DemoService;
import com.upuphone.cloudplatform.demo.dao.entity.AA;
import com.upuphone.cloudplatform.demo.dao.entity.User;
import com.upuphone.cloudplatform.demo.dao.mapper.UserMapper;
import com.upuphone.cloudplatform.demo.entity.Supplier;
import com.upuphone.cloudplatform.demo.web.converter.WebDemoConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/17 18:25
 */
@Slf4j
@RestController
@Api(value = "demo示例", tags = "demo示例")
@RequestMapping(value = "/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @Resource
    private DemoManager demoManager;

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Resource
    private NacosServiceManager nacosServiceManager;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/offline")
    public String offline() {
        String serviceName = nacosDiscoveryProperties.getService();
        String groupName = nacosDiscoveryProperties.getGroup();
        String clusterName = nacosDiscoveryProperties.getClusterName();
        String ip = nacosDiscoveryProperties.getIp();
        int port = nacosDiscoveryProperties.getPort();
        log.info("deregister from nacos, serviceName:{}, groupName:{}, clusterName:{}, ip:{}, port:{}", serviceName, groupName, clusterName, ip, port);
        try {
            NamingService namingService = this.nacosServiceManager.getNamingService(this.nacosDiscoveryProperties.getNacosProperties());
            namingService.deregisterInstance(serviceName, groupName, ip, port, clusterName);
        } catch (Exception e) {
            log.error("deregister from nacos error", e);
            return "error";
        }
        return "success";
    }

    @GetMapping("/index")
    @ApiOperation(value = "查询接口示例")
    public String index(@RequestParam("args") String args) {
        return demoService.demoExecute(args);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增接口示例")
    public DemoResponse insert(@RequestBody DemoRequest demoRequest) {
        return demoManager.doSomething(demoRequest);
    }

    @GetMapping("/select")
    @ApiOperation(value = "分页查询接口示例")
    public PageDTO<DemoResponse> select(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageDTO<DemoBo> demoBoPage = demoService.selectList(pageNum, pageSize);
        PageDTO<DemoResponse> responsePageDTO = WebDemoConverter.INSTANCE.bo2Dto(demoBoPage);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 1);
        queryWrapper.and(wrapper->wrapper.like("user_name", "ding").or().like("nick_name", "To"));
        System.out.println(userMapper.selectList(queryWrapper));
        return responsePageDTO;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口示例")
    public boolean delete(@RequestParam("id") Long id) {
        return demoService.delete(id);
    }

    @GetMapping("/redis")
    @ApiOperation(value = "redis测试")
    public String redis(@RequestParam("args") String args) {
        return demoService.redis(args);
    }

    @GetMapping("/rocketmq/producer")
    @ApiOperation(value = "rocketmq发送消息测试")
    public String rocketmqProducer(@RequestParam("topic") String topic) {
        return demoService.producerRocketmq(topic);
    }

    @GetMapping("/rocketmq/consumer")
    @ApiOperation(value = "rocketmq消费消息测试")
    public String rocketmqConsumer() {
        return demoService.consumerRocketmq();
    }

    @GetMapping("/send/email")
    @ApiOperation(value = "发送邮件")
    public DemoResponse sendEmail() {
        DemoResponse response = new DemoResponse();
        response.setId(1L);
        return response;
    }

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("aa");
        Supplier<String> a = () -> user.getUserName();

        System.out.println(a.sup());

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx235d9f517d4b4454&secret=c57ab0289ecf1c67d8f21f7c7135605e&code=123&grant_type=authorization_code";
        AA response = restTemplate.getForObject(url, AA.class);

        ObjectMapper mapper = new ObjectMapper();
    }

}
