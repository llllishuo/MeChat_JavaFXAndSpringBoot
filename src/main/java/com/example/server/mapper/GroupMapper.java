package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.Friend;
import com.example.server.entity.Group;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GroupMapper extends BaseMapper<Group> {


}
