package org.example.madspild.Service;


import org.example.madspild.Model.User;
import org.example.madspild.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean addUser(User p){
        boolean isSuccess = userRepository.addUser(p);
        return isSuccess;
    }

    public boolean doesUserExists(User p){
        return userRepository.doesUserExist(p.getUsername());
    }

}
