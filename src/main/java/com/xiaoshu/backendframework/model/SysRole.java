package com.xiaoshu.backendframework.model;

public class SysRole extends BaseEntity<Long> {

    private static final long serialVersionUID = -5268765857826955009L;

    private String name;

    private String description;


    public SysRole() {
    }

    public SysRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
