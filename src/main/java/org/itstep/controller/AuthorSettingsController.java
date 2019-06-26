package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class AuthorSettingsController {

    BlogService blogService;

    @Autowired
    public AuthorSettingsController(BlogService blogService){
        this.blogService = blogService;
    }
}
