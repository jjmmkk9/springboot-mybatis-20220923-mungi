package com.boot.mybatis20220923mungi.repository;

import com.boot.mybatis20220923mungi.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    int save(User user);

}
