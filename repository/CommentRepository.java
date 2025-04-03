package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
