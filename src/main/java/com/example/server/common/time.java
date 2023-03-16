package com.example.server.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

@Slf4j
@Data
public class time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    private int week;
    //时间戳
    private String timeStamp;
    private String dataTime;

    // 0 1 2 3 4 5 6 7 8 9 10  11  12   13  14  15  16  17  18
    // 2 0 0 2 . 1 1 . 1 1      1   1   :   1   1   :   1   1
    //初始化
    public time(String str) throws ParseException {

        this.year = Integer.parseInt(str.substring(0, 4));

        this.month = Integer.parseInt(str.substring(5, 7));
        this.day = Integer.parseInt(str.substring(8, 10));
        this.hour = Integer.parseInt(str.substring(11, 13));
        this.minute = Integer.parseInt(str.substring(14, 16));
        this.second = Integer.parseInt(str.substring(17));
        this.timeStamp = dateToStamp(str);
        this.week = dateToWeek()+1;

        this.dataTime = str;
    }

    //根据当前时间获取时间的显示形式
    public String getNewsTime() throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format =df.format(System.currentTimeMillis());
        time nowTime = new time(format);

        log.info("nowTime:"+this);

        if (nowTime.getYear() > this.year) {
            return this.year + "年" + this.month + "月" + this.day+"日";
        }
        if (nowTime.getMonth() > this.month) {
            return  this.month + "月" + this.day+"日";
        }
        if((nowTime.getDay()-this.day)>7){
            return nowTime.getDay()-this.day+"天前";
        }


        if((nowTime.getDay()-this.day)<=7){
            if(nowTime.getDay()==this.day){
                if(nowTime.getHour()>this.hour){
                    return this.hour+":"+this.minute;
                }
                return nowTime.getMinute() - this.minute + "分钟前";
            }
            if(nowTime.getDay()-this.day==1){
                return "昨天";
            }

            int w = (nowTime.getDay()-this.day)%7;
            String[] week = {"一","二","三","四","五","六","日"};

            return "星期"+week[w-1];
        }


        return "err";
    }

    //转为星期
    public int dateToWeek() {
        int day = this.day;
        int month = (this.month == 1 || this.month == 2) ? (this.month + 12) : this.month;
        int year = this.month > 12 ? this.year - 1 : this.year;
        return (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
    }

    //日期转为时间戳
    public static String dateToStamp(String s) throws ParseException {
        char charAt = s.charAt(4);
        String res;
        //设置时间模版
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + charAt + "MM" + charAt + "dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


}
