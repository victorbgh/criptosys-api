package br.com.criptosys.service.impl;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class EmailServiceImpl implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(UserDE userDE, String newPassword) {
        SimpleMailMessage sm = prepareNewPasswordEmail(userDE, newPassword);
        sendEmail(sm);
    }

    public SimpleMailMessage prepareNewPasswordEmail(UserDE userDE, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(userDE.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}