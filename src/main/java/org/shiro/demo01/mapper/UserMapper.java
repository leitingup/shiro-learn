package org.shiro.demo01.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.shiro.demo01.pojo.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public User queryUserByName(String username);
}
