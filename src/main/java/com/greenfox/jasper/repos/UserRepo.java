package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    public User findByKingdom(String kingdom);
}
