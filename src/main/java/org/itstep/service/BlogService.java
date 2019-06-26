package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.itstep.domain.entity.*;
import org.itstep.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlogService {
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private PostRepository postRepository;
    private TagRepository tagRepository;
    private CommentRepository commentRepository;
    private SubscriberRepository subscriberRepository;

    @Autowired
    public BlogService(AuthorRepository authorRepository, CategoryRepository categoryRepository,
                       PostRepository postRepository, TagRepository tagRepository, CommentRepository commentRepository,
                       SubscriberRepository subscriberRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    public Tag saveTag(Tag tag) {
        log.info("Save tag " + tag);
        return tagRepository.save(tag);
    }

    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Transactional
    public Category saveCategory(Category category) {
        log.info("Save category " + category);
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Author saveAuthor(Author author) {
        log.info("Save author " + author);
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public Post savePost(Post post) {
        log.info("Save post " + post);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        log.info("Save comment " + comment);
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Post> getLastPosts() {
        return postRepository.findAll(Sort.by(Sort.Order.desc("published")))
                .stream().limit(4).collect(Collectors.toList());
    }

    public List<Post> getAllPostsByCategoryId(Integer categoryId) {
        var category = categoryRepository.findById(categoryId).orElse(null);
        return postRepository.findAllByCategory(category);
    }

    public List<Post> getAllPostsByAuthorId(Integer authorId) {
        var author = authorRepository.findById(authorId).orElse(null);
        return postRepository.findAllByAuthor(author);
    }

    public List<Post> getAllPostsByTagId(Integer tagId) {
        return postRepository.findAllByTags_Id(tagId);
//    return postRepository.findAll()
//            .stream()
//            .filter(p -> p.getTags().stream().anyMatch( t -> t.getId().equals(tagId)))
//            .collect(Collectors.toList());
    }

    public Page<Post> getPosts(int page, int size){
        return postRepository.findAll(PageRequest.of(page,size));
    }


    public Page<Post> getPostsByQuery(String query, int page, int size) {
        return postRepository
                .findAllByTitleContainingIgnoreCaseOrTextContainingIgnoreCase(query, query, PageRequest.of(page, size));
    }
    public Page<Post> getAllPostsByDate(LocalDate published, int page, int size){
        return postRepository.findAllByPublishedIsBetween(published.atStartOfDay(), published.plusDays(1).atStartOfDay(), PageRequest.of(page, size));
    }

    public void subscribe(Subscriber subscriber){
        subscriberRepository.save(subscriber);
    }

    public void register(Author author){
        authorRepository.save(author);
    }
}
