package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Post;
import org.itstep.domain.entity.Subscriber;
import org.itstep.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    private BlogService blogService;

    @ModelAttribute(name = "lastPosts")
    public List<Post> lastPosts() {
        return blogService.getLastPosts();
    }

    @Autowired
    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path = "/")
    public String index(Model model,
                        @RequestParam(required = false, name = "category") Integer categoryId,// id категорий
                        @RequestParam(required = false, name = "author") Integer authorId,// id автора
                        @RequestParam(required = false, name = "tag") Integer tagId,// id тэга
                        @RequestParam(required = false, name = "page") Integer page,// номер страницы начиная с 0
                        @RequestParam(required = false, name = "size") Integer size) {// количество постов на странице
        log.info(String.format("category: %d, author: %d, tag: %d, page: %d, size: %d",
                categoryId, authorId, tagId, page, size));
        List<Post> allPosts;

        if (categoryId != null) {
            allPosts = blogService.getAllPostsByCategoryId(categoryId);
        } else if (authorId != null) {
            allPosts = blogService.getAllPostsByAuthorId(authorId);
        } else if (tagId != null) {
            allPosts = blogService.getAllPostsByTagId(tagId);
        } else {
            if (size == null) {
                size = 5;
            }
            if (page == null) {
                page = 0;
            }
            Page<Post> pagePosts = blogService.getPosts(page, size);//получение страницы с постами (заданной в запросе) заданного в размере 2 аргументе запроса
            allPosts = pagePosts.getContent();
            model.addAttribute("page", page);
            model.addAttribute("pages", pagePosts.getTotalPages());
        }
        model.addAttribute("posts", allPosts);
        model.addAttribute("url", "/");
        return "index";
    }

    @GetMapping(path = "/search")
    public String search(@RequestParam(required = false, name = "query") String query,
                         @RequestParam(required = false, name = "date") String published,
                         @RequestParam(required = false, name = "page") Integer page,
                         @RequestParam(required = false, name = "size") Integer size,
                         Model model) {
        log.info(query);
        if (size == null) {
            size = 5;
        }
        if (page == null) {
            page = 0;
        }
        Page<Post> postsByQuery = null;
        if (query != null) {
            postsByQuery = blogService.getPostsByQuery(query, page, size);
        } else {
            LocalDate parse = LocalDate.parse(published);
            postsByQuery = blogService.getAllPostsByDate(parse, page, size);
        }
        List<Post> allPosts = postsByQuery.getContent();
        model.addAttribute("posts", allPosts);
        model.addAttribute("page", page);
        model.addAttribute("pages", postsByQuery.getTotalPages());
        model.addAttribute("url", "/search?query=" + query);
        model.addAttribute("url", "/search?date=" + published);
        return "index";
    }

    @PostMapping(path = "/subscribe")
    public String subscribe(@ModelAttribute @Validated Subscriber subscriber, BindingResult result) {
        log.info("Subscribe " + subscriber);
        if (!result.hasErrors()){
            blogService.subscribe(subscriber);
        }
        return "redirect:/";
    }
}
