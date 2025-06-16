package com.emailsender.demo.domain.port;

import com.emailsender.demo.domain.model.MailSendDTO;

public interface EmailServicePort {
    String sendMail(MailSendDTO dto);
}
