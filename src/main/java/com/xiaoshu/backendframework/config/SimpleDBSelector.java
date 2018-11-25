package com.xiaoshu.backendframework.config;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.github.pagehelper.Dialect;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.github.pagehelper.dialect.helper.OracleDialect;
import com.github.pagehelper.dialect.helper.SqlServer2012Dialect;
import com.github.pagehelper.dialect.helper.SqlServerDialect;

public class SimpleDBSelector implements DBSelector {

    private String dbType;

    private Dialect dialect;

    public SimpleDBSelector() {
    }

    public SimpleDBSelector(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public String getDbType() {
        return dbType;
    }

    @Override
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public Dialect getDialect() {
        if (dialect == null) {
            if (dbType == null || "".equals(dbType)) {
                dialect = new MySqlDialect();
            } else {
                dbType = dbType.trim().toUpperCase();
                if (dbType.equals(DBType.MySQL.toString())) {
                    dialect = new MySqlDialect();
                } else if (dbType.equals(DBType.Oracel.toString())) {
                    dialect = new OracleDialect();
                } else if (dbType.equals(DBType.SQLServer.toString())) {
                    dialect = new SqlServerDialect();
                } else if (dbType.equals(DBType.SQLServer2012.toString())) {
                    dialect = new SqlServer2012Dialect();
                } else {
                    dialect = new MySqlDialect();
                }
            }
        }
        return dialect;
    }

    @Override
    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }


    public enum DBType {
        MySQL("MySQL"),
        Oracel("Oracel"),
        SQLServer("SQLServer"),
        SQLServer2012("SQLServer2012");

        private String value;

        private DBType(String value) {
            this.value = value;
        }

    }

}
