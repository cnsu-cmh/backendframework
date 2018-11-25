package com.xiaoshu.backendframework.config;

import com.github.pagehelper.Dialect;

public interface DBSelector {

    public String getDbType();

    public void setDbType(String dbType);

    public Dialect getDialect();

    public void setDialect(Dialect dialect);
}
