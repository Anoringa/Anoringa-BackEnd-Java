package com.pelucasistemas.anoringa.repository;


import com.pelucasistemas.anoringa.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
