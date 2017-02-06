package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.domain.security.Authority;
import com.greenfox.jasper.repos.UserAuthorityRepo;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistrationServices {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserAuthorityRepo userAuthorityRepo;

    public void registerNewUser(User user){
        List<Authority> authorities = user.getAuthorities();
        authorities.add(userAuthorityRepo.findOne((long)1));
        userRepo.save(user);
    }
}
