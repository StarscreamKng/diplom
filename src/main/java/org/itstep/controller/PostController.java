package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Comment;
import org.itstep.domain.entity.Post;
import org.itstep.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/post")
@Slf4j
public class PostController {

    private BlogService blogService;

    @ModelAttribute(name = "lastPosts")
    public List<Post> lastPosts() {
        return blogService.getLastPosts();
    }

    @Autowired
    public PostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path = "/{id}")
    public String singlePost(@PathVariable(required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/";
        }
        Post postById = blogService.getPostById(id);
        model.addAttribute("model", postById);
        model.addAttribute("comment", new Comment());
        return "post";
    }

    @PostMapping(path = "/{id}/comment")
    public String addComment(@PathVariable Integer id,
                             @Validated @ModelAttribute Comment comment, BindingResult bindingResult,
                             Model model) {
        // 0. Проверить нет ли ошибок привязки
        if (!bindingResult.hasErrors()) {
            // 1. Найти пост по id
            Post postById = blogService.getPostById(id);
            // 2. Добавить к найденному посту новый комментарий
            comment.setPost(postById);
            comment.setCommentDate(LocalDateTime.now());
            blogService.saveComment(comment);
            log.debug("Comment saved successfully");
            // 3. Сохранить в базу данных
            return "redirect:/post/" + id;
        }
        log.error("Error saved comment" + bindingResult.getAllErrors());
        model.addAttribute("error", bindingResult.getAllErrors());
        return "post";
    }
}
