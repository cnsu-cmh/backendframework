package com.xiaoshu.backendframework.service.impl;

import com.google.common.collect.Maps;
import com.xiaoshu.backendframework.dto.BeanField;
import com.xiaoshu.backendframework.dto.GenerateInput;
import com.xiaoshu.backendframework.service.GenerateService;
import com.xiaoshu.backendframework.util.SpringUtil;
import com.xiaoshu.backendframework.util.StringUtil;
import com.xiaoshu.backendframework.util.TemplateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * mysql类型与java类型部分对应关系
     */
    private static Map<String, String> map = Maps.newHashMap();

    static {
        map.put("int", Integer.class.getSimpleName());
        map.put("tinyint", Integer.class.getSimpleName());
        map.put("double", Double.class.getSimpleName());
        map.put("float", Float.class.getSimpleName());
        map.put("decimal", BigDecimal.class.getSimpleName());
        map.put("date", Date.class.getSimpleName());
        map.put("timestamp", Date.class.getSimpleName());
        map.put("datetime", Date.class.getSimpleName());
        map.put("varchar", String.class.getSimpleName());
        map.put("text", String.class.getSimpleName());
        map.put("longtext", String.class.getSimpleName());

    }


    private RowMapper<BeanField> beanFieldMapper = new RowMapper<BeanField>() {

        @Override
        public BeanField mapRow(ResultSet rs, int paramInt) throws SQLException {
            BeanField beanField = new BeanField();
            beanField.setColumnName(rs.getString("column_name"));
            beanField.setColumnType(rs.getString("data_type"));
            beanField.setColumnComment(rs.getString("column_comment"));
            beanField.setColumnDefault(rs.getString("column_default"));

            return beanField;
        }
    };

    @Override
    public List<BeanField> selectBeanField(String tableName) {
        DataSource dataSource = SpringUtil.getBean(DataSource.class);
        try {
            String driverName = dataSource.getConnection().getMetaData().getDriverName();
            System.out.println(driverName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select column_name, data_type, column_comment, column_default " +
                "FROM information_schema.columns " +
                "WHERE table_name= ? " +
                "and table_schema = (select database())";
        List<BeanField> beanFields = jdbcTemplate.query(sql,new String[] { tableName }, beanFieldMapper);
        if (CollectionUtils.isEmpty(beanFields)) {
            throw new IllegalArgumentException("表" + tableName + "不存在");
        }

        beanFields.parallelStream().forEach(b -> {
            b.setName(StringUtil.str2hump(b.getColumnName()));
            String type = map.get(b.getColumnType());
            if (type == null) {
                type = String.class.getSimpleName();
            }
            b.setType(type);
            if ("id".equals(b.getName())) {
                b.setType(Long.class.getSimpleName());
            }

            b.setColumnDefault(b.getColumnDefault() == null ? "" : b.getColumnDefault());
        });

        return beanFields;
    }

    @Override
    public void saveCode(GenerateInput input) {
        TemplateUtil.saveTemplete(input, TemplateUtil.TemplateType.CONTROLLER);
        TemplateUtil.saveTemplete(input, TemplateUtil.TemplateType.SERVICE);
        TemplateUtil.saveTemplete(input, TemplateUtil.TemplateType.SERVICEIMPL);
        TemplateUtil.saveHtmlList(input);
    }

    @Override
    public String upperFirstChar(String tableName) {
        String name = StringUtil.str2hump(tableName);
        String firstChar = name.substring(0, 1);
        name = name.replaceFirst(firstChar, firstChar.toUpperCase());

        return name;
    }
}
