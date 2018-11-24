package com.xiaoshu.backendframework.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class GeneratorUtil {

    public static void main(String[] args) {
        saveModelAndMapper();
    }

    public static void saveModelAndMapper() {

        try{
            List<String> warnings = new ArrayList<String>();

            boolean overwrite = true;

            ConfigurationParser cp = new ConfigurationParser(warnings);

            Configuration config = cp.parseConfiguration(getResourceAsStream("generator/generatorConfig.xml"));

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);

            for (String warning : warnings) {

                System.out.println(warning);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
