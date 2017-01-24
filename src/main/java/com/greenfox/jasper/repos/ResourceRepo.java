package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepo extends CrudRepository<Resource, String> {
}
