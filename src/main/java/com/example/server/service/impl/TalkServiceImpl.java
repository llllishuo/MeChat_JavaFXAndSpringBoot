package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Talk;
import com.example.server.mapper.TalkMapper;
import com.example.server.service.TalkService;
import org.springframework.stereotype.Service;

@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {
}
