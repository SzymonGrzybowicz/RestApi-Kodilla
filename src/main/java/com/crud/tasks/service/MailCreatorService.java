package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("functionality one");
        functionality.add("functionality two");
        functionality.add("functionality three");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:88/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "New TrelloCard created on Your Account");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyMail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "Daily review of Your Cards!");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("is_friend", true);
        return templateEngine.process("mail/daily_mail", context);
    }
}
