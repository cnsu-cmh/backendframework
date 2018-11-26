package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {

    List<Dict> selectByType(String type);

    Integer selectConditionCount(Map<String,Object> params);

    List<Dict> selectConditionList(Map<String,Object> params);

    Dict getById(Long id);

    Dict getByTypeAndK(String type, String k);

    Integer save(Dict dict);

    void update(Dict dict);

    void delete(Long id);
}
