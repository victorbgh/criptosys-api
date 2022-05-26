package br.com.criptosys.service.abtc;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNewPasswordEmail(UserDE userDE, String newPassword) {
        SimpleMailMessage sm = prepareNewPasswordEmail(userDE, newPassword);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(UserDE userDE, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(userDE.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}