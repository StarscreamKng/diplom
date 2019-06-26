package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Author;
import org.itstep.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegisterController {

    private BlogService blogService;

    @Autowired
    public RegisterController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        model.addAttribute("model", new Author());
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@ModelAttribute @Validated Author author, BindingResult bindingResult){
        log.info("New author: " + author);
        if (!bindingResult.hasErrors()){
            blogService.register(author);
        }
        return "redirect:/";
    }
}
