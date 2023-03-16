package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.server.common.R;
import com.example.server.common.time;
import com.example.server.entity.Friend;
import com.example.server.entity.Talk;
import com.example.server.entity.User;
import com.example.server.entity.dto.FriendListDto;
import com.example.server.service.FriendService;
import com.example.server.service.TalkService;
import com.example.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @Autowired
    private TalkService talkService;

    public User getUserById(int id){
        return userService.getById(id);
    }




    public void updateUser(User user){
        userService.save(user);
    }

    public R<String> updateSing(User user){
        if(user==null){
            return R.error("err");
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId,user.getId());
        lambdaUpdateWrapper.set(User::getSign,user.getSign());
        userService.update(lambdaUpdateWrapper);
        return R.success("修改成功");
    }


    //查询好友列表
    public List<FriendListDto> getFriendList(int id) throws ParseException {
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Friend::getUserId, id);
        List<Friend> list = friendService.list(queryWrapper);
        List<FriendListDto> friendList = new ArrayList<>();



        for (Friend item : list) {
            //好友信息 - name+headImage
            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();

            userQueryWrapper.eq(User::getId,item.getFriendId());
            User friend = userService.getOne(userQueryWrapper);
//            friendList.add(friend);
            //聊天记录 - 最新信息
            LambdaQueryWrapper<Talk> talkQueryWrapper = new LambdaQueryWrapper<>();
            talkQueryWrapper.eq(Talk::getSendTime,item.getOverTime());
            List<Talk> talkList = talkService.list(talkQueryWrapper);

            Talk news = new Talk();
            for (Talk talkItem :talkList) {
                news = talkItem;
                time time =  new time(news.getSendTime());
                news.setSendTime(time.getNewsTime());
            }

            FriendListDto friendDto = new FriendListDto();
            friendDto.setId(friend.getId());
            friendDto.setName(friend.getName());
            friendDto.setHeadImg(friend.getHeadImg());
            friendDto.setSendTime(news.getSendTime());
            friendDto.setContent(news.getContent());

            friendList.add(friendDto);
        }


        return friendList;
    }
}
