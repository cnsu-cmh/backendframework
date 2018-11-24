package com.xiaoshu.backendframework.model;

import com.xiaoshu.backendframework.util.StringUtil;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class BaseTransientEntity implements Serializable  {

    @Transient
    public String orderBy;

    @Transient
    public  Integer start;

    @Transient
    public Integer length;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = StringUtil.hump2Underline(orderBy);
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
