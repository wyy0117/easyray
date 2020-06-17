package com.easyray.mailprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
class MailProviderApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void test() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailProperties.getUsername());
        simpleMailMessage.setSubject("主题");
        simpleMailMessage.setText("这是内容");
        simpleMailMessage.setTo(mailProperties.getUsername());
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    void test1() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("测试 thymeleaf");
        Context context = new Context();
        context.setVariable("username", "张三");
        String mailContent = templateEngine.process("mail.html", context);
        mimeMessageHelper.setText(mailContent, true);

        mimeMessageHelper.setFrom(mailProperties.getUsername());

        mimeMessageHelper.setSentDate(new Date());

        mimeMessageHelper.setTo(mailProperties.getUsername());

        javaMailSender.send(mimeMessage);
    }

    @Test
    void testThymeleaf() {
        Context context = new Context();
        context.setVariable("username", "张三");
        String mailContent = templateEngine.process("mail.html", context);
        System.out.println(mailContent);
    }

}
