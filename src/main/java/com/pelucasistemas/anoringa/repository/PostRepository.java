package com.pelucasistemas.anoringa.repository;


import com.pelucasistemas.anoringa.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdEquals(Long id);
}
