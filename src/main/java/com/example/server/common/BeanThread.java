package com.example.server.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Controller
public class BeanThread {
    private Thread thread;

    @PostConstruct
    public void init() {
        // 初始化线程
        thread = new Thread(() -> {
            // 执行任务
        });
        thread.start();
    }

    @PreDestroy
    public void cleanup() {
        // 停止线程
        thread.interrupt();
    }
}
