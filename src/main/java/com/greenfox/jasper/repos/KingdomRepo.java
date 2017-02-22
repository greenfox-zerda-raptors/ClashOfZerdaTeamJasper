package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KingdomRepo extends CrudRepository<Kingdom, Long> {
    Kingdom findOne(long id);

    Kingdom findByName(String name);

    List<Kingdom> findAll();

    Kingdom findOneByUserId(long userId);
}
