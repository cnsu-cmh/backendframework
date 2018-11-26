package com.xiaoshu.backendframework.service;

import com.xiaoshu.backendframework.model.Mail;
import com.xiaoshu.backendframework.model.MailTo;

import java.util.List;
import java.util.Map;

public interface MailService {

    void save(Mail mail, List<String> toUser);

    Mail getById(Long id);

    List<MailTo> getToUsers(Long id);

    Integer selectConditionCount(Map<String,Object> params);

    List<Mail> selectConditionList(Map<String,Object> params);
}
