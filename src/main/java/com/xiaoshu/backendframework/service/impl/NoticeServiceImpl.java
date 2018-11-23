package com.xiaoshu.backendframework.service.impl;

import com.xiaoshu.backendframework.mapper.NoticeMapper;
import com.xiaoshu.backendframework.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Integer countUnread(Long userId) {
        return noticeMapper.countUnread(userId);
    }
}
