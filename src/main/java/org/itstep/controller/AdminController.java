package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.dto.PostDto;
import org.itstep.domain.entity.Author;
import org.itstep.domain.entity.Category;
import org.itstep.domain.entity.Post;
import org.itstep.domain.entity.Tag;
import org.itstep.service.BlogService;
import org.itstep.service.DtoConverter;
import org.itstep.service.EmailService;
import org.itstep.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/admin")
@Slf4j
public class AdminController {

    private EmailService emailService;
    private BlogService blogService;
    private DtoConverter dtoConverter;
    private UploadService uploadService;

    @Autowired
    public AdminController(BlogService blogService, DtoConverter dtoConverter, UploadService uploadService, EmailService emailService) {
        this.blogService = blogService;
        this.dtoConverter = dtoConverter;
        this.uploadService = uploadService;
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

    @GetMapping("/post")
    public String post(Model model) {
        log.info("Show all posts");
        model.addAttribute("model", new PostDto());
        return "admin/post/index";
    }

    @PostMapping("/post")
    public String create(@ModelAttribute @Validated PostDto postDto, BindingResult bindingResult,
                         @RequestParam("image") MultipartFile image) throws IOException {
        log.info("Save post: " + postDto);
        log.info("Save image: " + image);
        if (bindingResult.hasErrors()) {
            return "admin/post/index";
        }
        Post post = dtoConverter.convert(postDto);
        post.setImageUrl(uploadService.saveImage(image));
        blogService.savePost(post);
        return "redirect:/admin/post";
    }

    @GetMapping("/sendemails")
    public String sendEmails() {
        return "admin/sendemails";
    }

    @PostMapping("/sendemails")
    public String sendEmails(String subject, String message) {
        log.info("send email: subject: " + subject + " message: " + message);

        emailService.sendEmailToAllSubscribers(subject, message);

        return "redirect:/admin";
    }
}
