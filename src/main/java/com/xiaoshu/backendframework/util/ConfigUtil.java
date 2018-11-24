package com.xiaoshu.backendframework.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class ConfigUtil {

    private static PropertiesConfiguration propConfig;

    private static final ConfigUtil CONFIG = new ConfigUtil();

    private static boolean autoSave = true;

    private ConfigUtil() {
    }

    public static ConfigUtil getInstance(String propertiesFile) {

        init(propertiesFile);

        return CONFIG;
    }

    private static void init(String propertiesFile){
        try {
            propConfig = new PropertiesConfiguration(propertiesFile);
            //自动重新加载
            propConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
            //自动保存
            propConfig.setAutoSave(autoSave);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    public Object getValue(String key) {
        return propConfig.getProperty(key);
    }

    public void setProperty(String key, String value) {
        propConfig.setProperty(key, value);
    }

    public static void main(String[] args) {
        ConfigUtil configUtil = ConfigUtil.getInstance("generator/bgm.properties");
        Object tableName = configUtil.getValue("table.tableName");
        System.out.println("tableName:"+tableName.toString());
        configUtil.setProperty("table.tableName","file_info");
        tableName = configUtil.getValue("table.tableName");
        System.out.println("reloaded tableName:"+tableName.toString());
    }



}
