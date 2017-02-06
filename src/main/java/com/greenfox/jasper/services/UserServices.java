package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zoltán on 2017.02.06..
 */
@Service
public class UserServices {
    @Autowired
    UserRepo userRepo;

    public User findOneUser(int userId){
        return userRepo.findOne((long) userId);
    }

    public Iterable<User> findAllUsers(){
        return userRepo.findAll();
    }



    public void saveOneUser(User user){
        userRepo.save(user);
    }

}
