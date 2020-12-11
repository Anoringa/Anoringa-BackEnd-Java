package com.pelucasistemas.anoringa.repository;


import com.pelucasistemas.anoringa.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //public Optional<User> findByUsername(@Param("username") String username);

    List<User> findByUsernameIs(String username);
    List<User> findByUsernameEquals(String username);
    User findByUsername(String username);
}
