package org.itstep.service;

import org.itstep.domain.dto.PostDto;
import org.itstep.domain.entity.Author;
import org.itstep.domain.entity.Category;
import org.itstep.domain.entity.Post;
import org.itstep.domain.entity.Tag;
import org.itstep.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DtoConverter {
  private AuthorRepository authorRepository;
  private CategoryRepository categoryRepository;
  private PostRepository postRepository;
  private TagRepository tagRepository;
  private CommentRepository commentRepository;

  @Autowired
  public DtoConverter(AuthorRepository authorRepository, CategoryRepository categoryRepository,
                     PostRepository postRepository, TagRepository tagRepository, CommentRepository commentRepository) {
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.commentRepository = commentRepository;
  }

  public Post convert(PostDto postDto) {
    Author author = authorRepository.findById(postDto.getCategoryId()).orElse(null);
    Category category = categoryRepository.findById(postDto.getCategoryId()).orElse(null);
    List<Tag> tags = tagRepository.findAllById(postDto.getTagsId());

    return new Post(null,author,null, postDto.getTitle(),postDto.getText(),null,category,
            tags, postDto.isWideImage(), null);
  }
}
