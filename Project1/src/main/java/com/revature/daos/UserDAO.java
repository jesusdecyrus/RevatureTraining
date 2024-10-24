package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User DAO Interface
 * @author Cyrus De Jesus
 */
@Repository // Register Class As Bean
public interface UserDAO extends JpaRepository<User, Integer> {}
