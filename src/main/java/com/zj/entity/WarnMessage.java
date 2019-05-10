package com.zj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:45
 */
public class WarnMessage {
        private String content;
        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date timeStamp;
        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setTimeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
        }
        public Date getTimeStamp() {
            return timeStamp;
        }
}
