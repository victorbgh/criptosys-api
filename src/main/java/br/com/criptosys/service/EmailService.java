package br.com.criptosys.service;

import br.com.criptosys.domain.entity.UserDE;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(UserDE userDE, String newPassword);
}
