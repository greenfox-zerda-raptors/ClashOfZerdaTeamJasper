package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepo extends CrudRepository<Resource, Long> {
     List<Resource> findAllByKingdom(Kingdom kingdom);
}
