package com.xiaoshu.backendframework.mapper;

import com.xiaoshu.backendframework.model.Notice;
import com.xiaoshu.backendframework.util.MyMapper;
import org.apache.ibatis.annotations.Select;

public interface NoticeMapper extends MyMapper<Notice> {

    @Select("select count(1) from notice t " +
            "left join notice_read r on r.notice_id = t.id and r.user_id = #{userId} " +
            "where t.status = 1 and r.user_id is null")
    Integer countUnread(Long userId);
}
