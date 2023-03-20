package com.longxingyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longxingyu.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 龙星宇
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-03-07 20:01:47
 * @Entity com.longxingyu.pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @MapKey("id")
    Map<String, Object> selectByMap(Long id);

    List<User> selectAllByAge(@Param("age") Integer age);

    List<User> listAge();
}




