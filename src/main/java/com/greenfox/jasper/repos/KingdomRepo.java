package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import org.springframework.data.repository.CrudRepository;

public interface KingdomRepo extends CrudRepository<Kingdom, Long> {
        public Kingdom findByName(String name);
}
