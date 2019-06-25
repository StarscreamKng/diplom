package org.itstep.domain.repository;

import org.itstep.domain.entity.Comment;
import org.itstep.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
}
