package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Author;
import org.itstep.domain.entity.Category;
import org.itstep.domain.entity.Tag;
import org.itstep.service.BlogService;
import org.itstep.service.DtoConverter;
import org.itstep.service.EmailService;
import org.itstep.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
@Slf4j
public class AdminController {

    private EmailService emailService;
    private BlogService blogService;

    @Autowired
    public AdminController(BlogService blogService, EmailService emailService) {
        this.blogService = blogService;
        this.emailService = emailService;
    }

    @GetMapping
    public String index() {
        return "/admin/index";
    }

    @GetMapping("/category")
    public String category(Model model) {
        log.info("Show all categories");
        model.addAttribute("model", new Category());
        return "admin/category/index";
    }

    @PostMapping("/category")
    public String create(@ModelAttribute @Validated Category category, BindingResult bindingResult) {
        log.info("Save category: " + category);
        if (bindingResult.hasErrors()) {
            return "admin/category/index";
        }

        blogService.saveCategory(category);

        return "redirect:/admin/category";
    }


    @GetMapping("/tag")
    public String tag(Model model) {
        log.info("Show all tags");
        model.addAttribute("model", new Tag());
        return "admin/tag/index";
    }

    @PostMapping("/tag")
    public String create(@ModelAttribute @Validated Tag tag, BindingResult bindingResult) {
        log.info("Save tag: " + tag);
        if (bindingResult.hasErrors()) {
            return "admin/tag/index";
        }
        blogService.saveTag(tag);
        return "redirect:/admin/tag";
    }

    @GetMapping("/author")
    public String author(Model model) {
        log.info("Show all authors");
        model.addAttribute("model", new Author());
        return "admin/author/index";
    }

    @PostMapping("/author")
    public String create(@ModelAttribute @Validated Author author, BindingResult bindingResult) {
        log.info("Save author: " + author);
        if (bindingResult.hasErrors()) {
            return "admin/author/index";
        }
        blogService.saveAuthor(author);
        return "redirect:/admin/author";
    }

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @GetMapping("/sendemails")
    public String sendEmails() {
        return "admin/sendemails";
    }

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @PostMapping("/sendemails")
    public String sendEmails(String subject, String message) {
        log.info("send email: subject: " + subject + " message: " + message);

        emailService.sendEmailToAllSubscribers(subject, message);

        return "redirect:/admin";
    }
}
