package test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.demo.business.service.DemoService;
import com.upuphone.cloudplatform.demo.dao.entity.User;
import com.upuphone.cloudplatform.demo.dao.mapper.DemoMapper;
import com.upuphone.cloudplatform.demo.dao.mapper.UserMapper;
import com.upuphone.cloudplatform.demo.web.DemoWebApplication;
import com.upuphone.cloudplatform.demo.web.controller.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Classname UserTest
 * @Description TODO
 * @Date 2022/3/19 5:04 下午
 * @Created by gz-d
 */
@SpringBootTest(classes = DemoWebApplication.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private DemoController demoController;

    @Autowired
    private DemoService demoService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void a() {
        User usr = demoService.getOne(Wrappers.<User>lambdaQuery().like(User::getUserName, "3"),false);
        System.out.println(usr);
    }

    @Test
    public void b() {

    }
}
