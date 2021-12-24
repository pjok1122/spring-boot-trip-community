package project.board.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import project.board.entity.Article;
import project.board.entity.Comment;
import project.board.entity.Member;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"article"})
    Page<Comment> findByMember(Member member, Pageable pageable);

}