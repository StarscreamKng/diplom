package org.itstep.domain.repository;

import org.itstep.domain.entity.Author;
import org.itstep.domain.entity.Category;
import org.itstep.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByCategory(Category category);
    List<Post> findAllByAuthor(Author author);
    List<Post> findAllByTags_Id(Integer id);
    Page<Post> findAllByTitleContainingIgnoreCaseOrTextContainingIgnoreCase(String title, String text, Pageable pageable);

    Page<Post> findAllByPublishedIsBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
