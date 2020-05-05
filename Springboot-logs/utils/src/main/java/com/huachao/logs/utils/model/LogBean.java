package com.huachao.logs.utils.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huachao
 */
@Data
@NoArgsConstructor
public class LogBean {
    public final static ThreadLocal<LogBean> threadLocal = new ThreadLocal();

    private String rid,sid,tid,from,message , ip , url;
    private Long time;

    public LogBean(String rid, String sid, String tid, String from, String message, String ip, String url) {
        this.rid = rid;
        this.sid = sid;
        this.tid = tid;
        this.from = from;
        this.message = message;
        this.ip = ip;
        this.url = url;
        threadLocal.set(this);
    }

    @Override
    public String toString() {
        this.time = System.currentTimeMillis();
        return JSON.toJSONString(this);
    }
}
