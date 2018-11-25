package com.xiaoshu.backendframework.model;

import com.xiaoshu.backendframework.annotation.OnCreateDate;
import com.xiaoshu.backendframework.annotation.OnUpdateDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 2054813493011812469L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @OnCreateDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OnCreateDate
    @OnUpdateDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    public String orderBy;

    @Transient
    public  Integer start;

    @Transient
    public Integer length;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
