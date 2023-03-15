package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Group;
import com.example.server.mapper.GroupMapper;
import com.example.server.service.GroupService;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
}
