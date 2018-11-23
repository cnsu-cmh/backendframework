package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.Dict;

import java.util.List;

public interface DictService {

    List<Dict> selectByType(String type);
}
