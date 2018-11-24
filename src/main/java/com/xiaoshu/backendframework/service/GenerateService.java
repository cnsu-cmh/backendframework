package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.dto.BeanField;
import com.xiaoshu.backendframework.dto.GenerateInput;

import java.util.List;

public interface GenerateService {

    String upperFirstChar(String tableName);

    List<BeanField> selectBeanField(String tableName);

    void saveCode(GenerateInput input);
}
