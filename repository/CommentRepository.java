package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.entity.Plan;
import com.example.scheduleproject.exception.NullResponseException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPlanId(Long id);

    default Comment findCommentByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new NullResponseException("Dose not exist comment by id =" + id));
    }
}
