package com.xiaoshu.backendframework.dto;

import java.io.Serializable;
import java.util.List;

public class GenerateInput implements Serializable {

    private static final long serialVersionUID = -6214833548928563948L;

    /**
     * 保存路径
     */
    private String path;

    private String tableName;

    /**
     * bean包名
     */
    private String beanPackageName;

    /**
     * java类名
     */
    private String beanName;

    /**
     * mapper包名
     */
    private String mapperPackageName;

    /**
     * mapper类名
     */
    private String mapperName;

    /**
     * service包名
     */
    private String servicePkgName;

    /**
     * service类名
     */
    private String serviceName;

    /**
     * serviceImpl包名
     */
    private String serviceImplPkgName;

    /**
     * serviceImpl类名
     */
    private String serviceImplName;

    /**
     * controller包名
     */
    private String controllerPkgName;

    /**
     * controller类名
     */
    private String controllerName;

    /**
     * 字段名
     */
    private List<String> columnNames;

    /**
     * 属性名
     */
    private List<String> beanFieldName;

    /**
     * 成员变量类型
     */
    private List<String> beanFieldType;

    /**
     * 默认值
     */
    private List<String> beanFieldValue;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanPackageName() {
        return beanPackageName;
    }

    public void setBeanPackageName(String beanPackageName) {
        this.beanPackageName = beanPackageName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServicePkgName() {
        return servicePkgName;
    }

    public void setServicePkgName(String servicePkgName) {
        this.servicePkgName = servicePkgName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplPkgName() {
        return serviceImplPkgName;
    }

    public void setServiceImplPkgName(String serviceImplPkgName) {
        this.serviceImplPkgName = serviceImplPkgName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerPkgName() {
        return controllerPkgName;
    }

    public void setControllerPkgName(String controllerPkgName) {
        this.controllerPkgName = controllerPkgName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getBeanFieldName() {
        return beanFieldName;
    }

    public void setBeanFieldName(List<String> beanFieldName) {
        this.beanFieldName = beanFieldName;
    }

    public List<String> getBeanFieldType() {
        return beanFieldType;
    }

    public void setBeanFieldType(List<String> beanFieldType) {
        this.beanFieldType = beanFieldType;
    }

    public List<String> getBeanFieldValue() {
        return beanFieldValue;
    }

    public void setBeanFieldValue(List<String> beanFieldValue) {
        this.beanFieldValue = beanFieldValue;
    }

}
