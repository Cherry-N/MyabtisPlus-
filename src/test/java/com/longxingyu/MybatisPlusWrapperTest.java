package com.longxingyu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longxingyu.mapper.UserMapper;
import com.longxingyu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * {@code @Create:} 2023-03-08-15:43:16
 * {@code @Author:} 爱睡觉的小龙堡 ~
 * {@code @ToUser:} Be Happy EveryDay
 * --------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        // 查询用户名包含a 年龄在20到30之间 邮箱信息不为null的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> notNull = userQueryWrapper
                .like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 查询用户信息 按照年龄的降序排序 若年龄相同 则按照id升序排位
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper1 = userQueryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(userQueryWrapper1);
        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
        // 删除name包含lxy的信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "lxy");
        int delete = userMapper.delete(userQueryWrapper);
        System.out.println(delete);
    }

    @Test
    public void test04() {
        // 将（年龄大于20并且用户名中包含a）或邮箱为null的用户信息进行修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age", 20)
                .like("name", "lxy")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("Locv");
        user.setEmail("locv@gmail.com");
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println(update);
    }

    @Test
    public void test05() {
        // 将用户名中包含a并且(年龄大于20或者邮箱为null)的用户信息进行修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "lxy")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setEmail("user@example.com");
        user.setName("Love");
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println(update);
    }

    @Test
    public void test06() {
        // 查询用户的用户名年龄邮箱
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        // 查询id<=7的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id", "select id from user where id <= 7");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    public void test08() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //isNotBlank判断某个字符创是否不为空字符串、不为null、不为空白符
            queryWrapper.like("user_name", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10() {
        String username = "L";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .lt(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }


}
