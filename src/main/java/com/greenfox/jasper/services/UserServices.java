package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.06..
 */
@Service
public class UserServices {
    @Autowired
    UserRepo userRepo;
    @Autowired
    DTOServices dtoServices;

    public User findOneUser(int userId){
        return userRepo.findOne((long) userId);
    }

    public Iterable<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User findeOneUserByName(String username) {
        return userRepo.findByUsername(username);
    }

    public User findOneUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findOneUserByKingdom(String kingdom) {
        return userRepo.findByKingdom(kingdom);
    }

    public void saveOneUser(User user){
        userRepo.save(user);
    }

    public KingdomDto returnKingdomByUsername(String username){
        try{
            return dtoServices.convertKingdomToDTO(userRepo.findByUsername(username).getKingdom());
        } catch(NullPointerException e){return null;}
    }
    public User findOneUser(long userId) {
        return userRepo.findOne(userId);
    }

    public List<User> findAllByScore() {
        return userRepo.findAll();
    }
}
