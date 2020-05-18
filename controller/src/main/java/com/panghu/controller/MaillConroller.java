package com.panghu.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.controller
 * @ClassName: MaillConroller
 * @Author: wxy
 * @Description: 发送邮件模板
 * @Date: 2020/5/11 23:38
 * @Version: 1.0
 */
@RestController
public class MaillConroller {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostMapping("/mailTest")
    public void mailTest() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("827928660@qq.com");
        helper.setTo("2398556764@qq.com");
        InternetAddress[] internetAddressCC = InternetAddress.parse("827928660@qq.com,2398556764@qq.com");
//        helper.setCc("827928660@qq.com,2398556764@qq.com");
        mimeMessage.setRecipients(Message.RecipientType.CC, internetAddressCC);
        helper.setSubject("主题：模板邮件");
        Map<String, Object> model = new HashMap<>();
        model.put("username", "didi");
        model.put("projectCode", "123");
        model.put("projectName", "123");
        model.put("telephone", "321");
        model.put("date", "2010-01-01");
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("template.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        helper.setText(text,true);
        mailSender.send(mimeMessage);
    }

}
