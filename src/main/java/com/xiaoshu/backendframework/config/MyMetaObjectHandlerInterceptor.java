package com.xiaoshu.backendframework.config;

import com.xiaoshu.backendframework.annotation.OnCreateDate;
import com.xiaoshu.backendframework.annotation.OnUpdateDate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.*;

@Intercepts({@Signature(type = StatementHandler.class,
                        method = "update",
                        args = {Statement.class }),
            @Signature(type=StatementHandler.class,
                        method="query",
                        args={Statement.class, ResultHandler.class}),
            @Signature(type=StatementHandler.class,
                        method="batch",
                        args={Statement.class}),
            @Signature(type = Executor.class,
                        method = "update",
                       args = {MappedStatement.class,Object.class })})
@Component
public class MyMetaObjectHandlerInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object arg = invocation.getArgs()[0];
        if(arg instanceof  MappedStatement) {
            MappedStatement ms  = (MappedStatement) arg;
            SqlCommandType sqlCommandType = ms.getSqlCommandType();
            Object object = invocation.getArgs()[1];
            if(object instanceof Map) {
                Map map = (Map) object;
                if (map.containsKey("collection")) {
                    List objectList = (List) map.get("collection");
                    for (Object obj : objectList) {
                        setProperty(sqlCommandType, obj);
                    }
                }
            }else {
                setProperty(sqlCommandType, object);
            }
        }

        long startTime = System.currentTimeMillis();
        Method method = invocation.getMethod();
        try{
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("[TimerInterceptor] execute [" + method.getName() + "] cost [" + (endTime - startTime) + "] ms");
        }

    }

    private void setProperty(SqlCommandType sqlCommandType, Object obj) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Field> fields = getDeclaredField(obj, sqlCommandType);
        for (Field field : fields) {
                BeanUtils.setProperty(obj, field.getName(), new Date());
        }
    }

    public List<Field> getDeclaredField(Object object,SqlCommandType sqlCommandType){
        if(SqlCommandType.INSERT == sqlCommandType) {
            return getOnCreateDateFields(object,sqlCommandType);
        }

        if(SqlCommandType.UPDATE == sqlCommandType) {
            return getOnUpdateDateFields(object,sqlCommandType);
        }

        return  new ArrayList<>();
    }

    private List<Field> getOnCreateDateFields(Object object, SqlCommandType sqlCommandType) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = object.getClass() ;
        for (Field field : clazz.getDeclaredFields()) {
            if(field.getAnnotation(OnCreateDate.class) != null) {
                fields.add(field);
            }
        }
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if(field.getAnnotation(OnCreateDate.class) != null) {
                    fields.add(field);
                }
            }
        }
        return fields;
    }

    private List<Field> getOnUpdateDateFields(Object object, SqlCommandType sqlCommandType) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = object.getClass() ;
        for (Field field : clazz.getDeclaredFields()) {
            if(field.getAnnotation(OnUpdateDate.class) != null) {
                fields.add(field);
            }
        }
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if(field.getAnnotation(OnUpdateDate.class) != null) {
                    fields.add(field);
                }
            }
        }
        return fields;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
