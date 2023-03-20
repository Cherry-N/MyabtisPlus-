package com.longxingyu;

import com.longxingyu.pojo.User;
import com.longxingyu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code @Create:} 2023-03-08-12:56:52
 * {@code @Author:} 爱睡觉的小龙堡 ~
 * {@code @ToUser:} Be Happy EveryDay
 * --------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    UserService userService;

    @Test
    void testGetCount() {
        int count = userService.count();
        System.out.println(count);
    }

    @Test
    void testInsertMore() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("lxy" + i);
            user.setAge(20 + i);
            user.setEmail("lxy.com" + i);
            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }
}
