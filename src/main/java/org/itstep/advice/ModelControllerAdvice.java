package org.itstep.advice;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Author;
import org.itstep.domain.entity.Category;
import org.itstep.domain.entity.Post;
import org.itstep.domain.entity.Tag;
import org.itstep.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ModelControllerAdvice {

  private final BlogService blogService;

  @Autowired
  public ModelControllerAdvice(BlogService blogService) {
    this.blogService = blogService;
  }

  @ModelAttribute(name = "tags")
  public List<Tag> getTags() {
    return blogService.getAllTags();
  }

  @ModelAttribute(name = "categories")
  public Iterable<Category> getCategories(){return blogService.getAllCategories();}

  @ModelAttribute(name = "authors")
  public Iterable<Author> getAuthors(){return blogService.getAllAuthors();}

//  @ModelAttribute(name = "posts")
//  public List<Post> getPosts(){return blogService.getAllPosts();}

  @ExceptionHandler
  public ModelAndView errorHandler(Throwable throwable) {
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("message", throwable);
    log.error("Error", throwable);
    return modelAndView;
  }
}
