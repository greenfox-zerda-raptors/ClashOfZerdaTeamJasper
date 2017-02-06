package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.security.Authority;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorityRepo extends CrudRepository <Authority, Long>{
}