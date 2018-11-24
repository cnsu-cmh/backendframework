package com.xiaoshu.backendframework.util;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

public class MySelectConditionProvider extends MapperTemplate {

    public MySelectConditionProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectConditionCount(MappedStatement statement) {
        StringBuilder sql = new StringBuilder();

        //获取tableName
        Class<?> entityClass = super.getEntityClass(statement);
        String tableName = super.tableName(entityClass);

        //拼接Select count语句
        sql.append(SqlHelper.selectCount(entityClass));

        //拼接from语句
        String fromTable = SqlHelper.fromTable(entityClass, tableName);
        sql.append(SqlHelper.fromTable(entityClass, super.tableName(entityClass)));

        //拼接where语句
        sql.append(whereAllIfColumnsLike(entityClass, super.isNotEmpty(), Boolean.FALSE));
        return sql.toString();
    }

    public String selectConditionList(MappedStatement statement) {

        StringBuilder sql = new StringBuilder();

        //获取tableName
        Class<?> entityClass = super.getEntityClass(statement);
        String tableName = super.tableName(entityClass);

        super.setResultType(statement, entityClass);

        //拼接Select Columns语句
        sql.append(SqlHelper.selectAllColumns(entityClass));

        //拼接from语句
        String fromTable = SqlHelper.fromTable(entityClass, tableName);
        sql.append(SqlHelper.fromTable(entityClass, super.tableName(entityClass)));

        //拼接where语句
        sql.append(whereAllIfColumnsLike(entityClass, super.isNotEmpty(), Boolean.FALSE));

        //拼接orderBy语句
        sql.append("<choose>");
        sql.append("<when test=\"orderBy != null and orderBy != '' \">");
        sql.append("${orderBy}");
        sql.append("</when>");
        sql.append("<otherwise>");
        sql.append(SqlHelper.orderByDefault(entityClass));
        sql.append("</otherwise>");
        sql.append("</choose>");

        //拼接limit语句
        sql.append("<if test=\"start != null and length != '' \">");
        sql.append(" limit ${start},${length}");
        sql.append("</if>");

        return sql.toString();

    }

    public String whereAllIfColumnsLike(Class<?> entityClass, boolean empty, boolean useVersion) {
        StringBuilder sql = new StringBuilder();
        sql.append("<where>");
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnSet.iterator();

        while(true) {
            EntityColumn column;
            do {
                if (!var5.hasNext()) {
                    if (useVersion) {
                        sql.append(SqlHelper.whereVersion(entityClass));
                    }

                    sql.append("</where>");
                    return sql.toString();
                }

                column = (EntityColumn)var5.next();
            } while(useVersion && column.getEntityField().isAnnotationPresent(Version.class));

            sql.append(SqlHelper.getIfNotNull(column, " AND " + getColumnLikeHolder(column), empty));
        }
    }

    public String getColumnLikeHolder(EntityColumn column) {
        if(column.getJavaType().getName().equals(String.class.getName())) {
            return column.getColumn() + " like concat('%',"+ column.getColumnHolder((String)null)+ ", '%') ";
        } else {
            return column.getColumn() + " = " + column.getColumnHolder((String)null);
        }
    }

}
