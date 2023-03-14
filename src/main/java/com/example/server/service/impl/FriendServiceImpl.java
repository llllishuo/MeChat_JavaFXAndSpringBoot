package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Friend;
import com.example.server.mapper.FriendMapper;
import com.example.server.service.FriendService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
}
