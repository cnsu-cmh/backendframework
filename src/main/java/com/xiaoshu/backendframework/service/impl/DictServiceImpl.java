package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.mapper.DictMapper;
import com.xiaoshu.backendframework.model.Dict;
import com.xiaoshu.backendframework.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> selectByType(String type) {
        Dict dict = new Dict(type);
        return dictMapper.select(dict);
    }
}
