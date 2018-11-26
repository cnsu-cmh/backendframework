package com.xiaoshu.backendframework.service;

import javax.mail.MessagingException;

public interface SendMailSevice {

    void sendMail(String u, String subject, String content) throws MessagingException;
}
