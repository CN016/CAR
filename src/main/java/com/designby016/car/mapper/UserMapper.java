package com.designby016.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.designby016.car.object.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
