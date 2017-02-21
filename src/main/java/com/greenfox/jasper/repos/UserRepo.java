package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByKingdom(String kingdom);
    User findByEmail(String email);

    List<User> findAllOrderByPointsDesc();
}
