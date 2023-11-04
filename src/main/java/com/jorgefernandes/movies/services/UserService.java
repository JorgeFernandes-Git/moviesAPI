package com.jorgefernandes.movies.services;

import com.jorgefernandes.movies.domain.user.User;
import com.jorgefernandes.movies.dtos.UserDTO;
import com.jorgefernandes.movies.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public boolean deleteUser(ObjectId userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        } else {
            return false; // user not found
        }
    }
}
