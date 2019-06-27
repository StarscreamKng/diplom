package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.dto.PostDto;
import org.itstep.domain.entity.Post;
import org.itstep.service.BlogService;
import org.itstep.service.DtoConverter;
import org.itstep.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/control")
public class ControlPanelController {

    private BlogService blogService;
    private DtoConverter dtoConverter;
    private UploadService uploadService;

    @Autowired
    public ControlPanelController(BlogService blogService, DtoConverter dtoConverter, UploadService uploadService) {
        this.blogService = blogService;
        this.dtoConverter = dtoConverter;
        this.uploadService = uploadService;
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
}
