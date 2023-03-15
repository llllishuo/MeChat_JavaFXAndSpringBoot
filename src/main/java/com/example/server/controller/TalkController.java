package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.server.entity.Friend;
import com.example.server.entity.Talk;
import com.example.server.service.FriendService;
import com.example.server.service.GroupService;
import com.example.server.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class TalkController {



    @Autowired
    private TalkService talkService;


    @Autowired
    private FriendService friendService;

    @Autowired
    private GroupService groupService;


    public List<Talk> getMsgListById(int id1, int id2){
        LambdaQueryWrapper<Talk> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper.eq(Talk::getFromId, id1)
                        .eq(Talk::getToId, id2))
                .or(wrapper -> wrapper.eq(Talk::getFromId, id2)
                        .eq(Talk::getToId, id1))
                .orderByAsc(Talk::getSendTime);
        return talkService.list(queryWrapper);

    }


    public void sendTalk(int fromId,int toId,String text,int isGroup) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format =df.format(System.currentTimeMillis());
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper.eq(Friend::getUserId, fromId)
                        .eq(Friend::getFriendId, toId))
                .or(wrapper -> wrapper.eq(Friend::getUserId, fromId)
                        .eq(Friend::getFriendId, text));
        Friend one = friendService.getOne(queryWrapper);
        if(one.getStartTime().isEmpty()){
            one.setStartTime(format);
            one.setOverTime(format);
        }else {
            one.setOverTime(format);
        }

        LambdaUpdateWrapper<Friend> friendUpdateWrapper = new LambdaUpdateWrapper<>();
        friendUpdateWrapper.eq(Friend::getUserId,one.getUserId())
                .eq(Friend::getFriendId,one.getFriendId());
        friendUpdateWrapper.set(Friend::getStartTime,one.getStartTime())
                .set(Friend::getOverTime,one.getOverTime());
        friendService.update(friendUpdateWrapper);
        friendUpdateWrapper = new LambdaUpdateWrapper<>();
        friendUpdateWrapper.eq(Friend::getUserId,one.getFriendId())
                .eq(Friend::getFriendId,one.getUserId());
        friendUpdateWrapper.set(Friend::getStartTime,one.getStartTime())
                .set(Friend::getOverTime,one.getOverTime());
        friendService.update(friendUpdateWrapper);


        Talk talk = new Talk();
        //talkService.count()+101,fromId,toId,text,format,group.getGroupId()
        talk.setId(talkService.count()+1+101);
        talk.setFromId(fromId);
        talk.setToId(toId);
        talk.setContent(text);
        talk.setSendTime(format);
        talk.setIsGroup(0);

        talkService.save(talk);
    }






}
