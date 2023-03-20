package com.longxingyu;

import com.longxingyu.mapper.UserMapper;
import com.longxingyu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MyabtisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setAge(20);
        user.setName("我爱的人");
        user.setEmail("woaideren.qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println("id=" + user.getId());
    }

    @Test
    void testDelete1() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "我爱的人");
        stringObjectMap.put("age", "20");
        // 根据map集合中设置的条件删除信息
        int i = userMapper.deleteByMap(stringObjectMap);
        System.out.println(i);
    }

    @Test
    void testDelete2() {
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        int i = userMapper.deleteBatchIds(longs);
        System.out.println(i);
    }

    @Test
    void testupdate() {
        User user = new User();
        user.setId(5L);
        user.setName("VzUpdate");
        user.setAge(18);
        user.setEmail("Vz@sina.com");
        int result = userMapper.updateById(user);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);

    }

    @Test
    void selectByMyself() {
        Map<String, Object> stringObjectMap = userMapper.selectByMap(5L);
        System.out.println(stringObjectMap);
    }

}
