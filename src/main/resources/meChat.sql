-- meChat.`login`
create table if not exists meChat.`login`
(
`id` varchar(256) not null comment 'uuid' primary key,
`username` varchar(10) not null comment '用户名',
`password` varchar(15) not null comment '密码',
`isdelete` int default 0 not null comment '是否注销'
) comment 'meChat.`login`';

insert into meChat.`login` (`id`, `username`, `password`) values ('f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'Apk', 'RT');
insert into meChat.`login` (`id`, `username`, `password`) values ('04d90352-4bdc-4ed8-9962-c56a6c9bcc97', 'wD3', 'XJrJ');
insert into meChat.`login` (`id`, `username`, `password`) values ('9388994a-33a9-4719-bbd1-a9677f4ac391', 'SbJ8', 'qe7');
insert into meChat.`login` (`id`, `username`, `password`) values ('8506b114-8117-4fcd-975e-12919cad6155', 'C7Tz', 'TD');
insert into meChat.`login` (`id`, `username`, `password`) values ('e655d637-ed11-458c-a4a7-0fe394440138', 'Qb', 'r7B8L');
insert into meChat.`login` (`id`, `username`, `password`) values ('b2739ce2-bafd-406b-bc1c-16744326a5c5', '7QHr', 'kfn');


-- meChat.`user`
create table if not exists meChat.`user`
(
`id` varchar(256) not null comment 'uuid' primary key,
`username` varchar(10) not null comment '用户名',
`name` varchar(15) not null comment '网名',
`fantasticid` varchar(256) null comment '个性签名',
`headimg` varchar(256) not null comment '头像',
`islogin` int default 1 not null comment '是否在线'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'Apk', '魏鹏煊', '8kq', 'www.rey-walker.com');
insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('04d90352-4bdc-4ed8-9962-c56a6c9bcc97', 'wD3', '何煜城', 'W6', 'www.abbie-kilback.net');
insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('9388994a-33a9-4719-bbd1-a9677f4ac391', 'SbJ8', '赵黎昕', 'hm', 'www.veta-hane.name');
insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('8506b114-8117-4fcd-975e-12919cad6155', 'C7Tz', '雷博文', '4WgW', 'www.susy-waters.biz');
insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('e655d637-ed11-458c-a4a7-0fe394440138', 'Qb', '龙炎彬', 'qpC', 'www.elina-schaefer.biz');
insert into meChat.`user` (`id`, `username`, `name`, `fantasticid`, `headimg`) values ('b2739ce2-bafd-406b-bc1c-16744326a5c5', '7QHr', '周晓博', '0HZPe', 'www.jose-hoppe.net');



-- meChat.`blog`
create table if not exists meChat.`blog`
(
`userId` varchar(256) not null comment 'uuid' ,
`blogId` varchar(256) not null comment '单条id' primary key,
`blogContent` varchar(256) not null comment '朋友圈内容',
`foundTime` datetime not null comment '朋友圈发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into meChat.`blog` (`userId`, `blogId`, `blogContent`, `foundTime`) values ('f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'b2739ce2-bafd-406b-bc1c-16744326a5c5', '转节', '2022-10-23 00:35:34');
insert into meChat.`blog` (`userId`, `blogId`, `blogContent`, `foundTime`) values ('f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', '04d90352-4bdc-4ed8-9962-c56a6c9bcc97', '毛驴', '2022-06-24 22:10:02');
insert into meChat.`blog` (`userId`, `blogId`, `blogContent`, `foundTime`) values ('f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', '九间', '2022-06-15 21:19:36');


-- meChat.`talk`
create table if not exists meChat.`talk`
(
`Id` varchar(256) not null comment 'id' primary key,
`fromId` varchar(256) not null comment '发送者id',
`toId` varchar(256) not null comment '接收者id',
`content` varchar(256) not null comment '对话内容',
`sendTime` datetime not null comment '发送时间',
`isGroup` int default 0 not null comment '是否为群组'
) comment 'meChat.`talk`';


insert into meChat.`talk` (`Id`, `fromId`, `toId`, `content`, `sendTime`) values ('b2739ce2-bafd-406b-bc1c-16744326a5c5', 'f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', '04d90352-4bdc-4ed8-9962-c56a6c9bcc97', 'vMqE', '2022-08-31 03:39:32');
insert into meChat.`talk` (`Id`, `fromId`, `toId`, `content`, `sendTime`) values ('b2712ce2-bafd-306b-bc1c-31157326a5c5', '04d90352-4bdc-4ed8-9962-c56a6c9bcc97', 'f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'xDAPk', '2022-04-18 21:09:17');
insert into meChat.`talk` (`Id`, `fromId`, `toId`, `content`, `sendTime`) values ('c9863f8b-58cd-4e5a-87fa-912580d16d9a', '9388994a-33a9-4719-bbd1-a9677f4ac391', '8506b114-8117-4fcd-975e-12919cad6155', 'mx', '2022-12-09 01:20:52');



-- meChat.`friend`
create table if not exists meChat.`friend`
(
`id` int not null comment 'id' primary key,
`userId` int not null comment '好友id' ,
`friendId` int not null comment '好友id' ,
`isDelete` int default 0 not null comment '是否还是好友'
) comment 'meChat.`friend`';



insert into meChat.`friend` (`id`, `friendId`, `userId`) values (8, 48695212, 78);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (8819140044, 706994323, 17801977);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (5, 3537, 7002);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (76, 56227031, 496159297);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (8527, 28530780, 97025);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (26, 77, 233);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (479320746, 4595381789, 53879351);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (516, 25786660, 3);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (92015, 2163130, 80372);
insert into meChat.`friend` (`id`, `friendId`, `userId`) values (439, 428, 3403294403);

-- meChatf.`group`
create table if not exists meChat.`group`
(
`groupId` int not null comment '群id' primary key,
`groupName` varchar(256) not null comment '群名',
`memberId` int not null comment '成员id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (494, '严风华', 4210238098);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (72, '钱睿渊', 4644647);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (1349, '杜明哲', 26);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (4, '沈懿轩', 41);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (2994802282, '高烨伟', 30);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (1, '龚志泽', 5219606998);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (91498, '程弘文', 426236);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (309139, '苏鹏涛', 616);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (2, '孔昊然', 95);
insert into meChatf.`group` (`groupId`, `groupName`, `memberId`) values (367, '孙修杰', 33512);
