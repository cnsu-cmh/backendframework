package com.xiaoshu.backendframework.model;

import com.xiaoshu.backendframework.annotation.OnCreateDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class SysLogs extends BaseTransientEntity {

    private static final long serialVersionUID = -1546981699576543612L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OnCreateDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private SysUser user;
    private String module;
    private Boolean flag;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
