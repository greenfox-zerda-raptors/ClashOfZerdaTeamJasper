package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface KingdomRepo extends CrudRepository<Kingdom, Long> {
}
