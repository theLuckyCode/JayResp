package com.example.vhr.bean;

import java.util.List;

//该类的作用是负责封装分页的信息
public class RespPageBean {
//  查出来的总数
    private Long total;
//  具体数据
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
