package org.shiro.demo01;

import org.junit.jupiter.api.Test;
import org.shiro.demo01.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Demo01ApplicationTests {
    @Autowired
    private UserServiceImpl userService;


    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("leiting"));
    }

}
