package com.project.examserver.service;

import com.project.examserver.domain.Role;
import com.project.examserver.domain.User;
import com.project.examserver.exception.UserAlreadyExist;
import com.project.examserver.exception.UserNotFound;
import com.project.examserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExist {
        User isExistUser = userRepository.findByEmail(user.getEmail());
        if(isExistUser == null) {
            Set<Role> allRoles = new HashSet<>();
            allRoles.add(new Role("NORMAL", "Normal Role For Website"));
            user.setRoles(allRoles);
            user.setProfile("default.png");
            return userRepository.save(user);
        }else{
            throw new UserAlreadyExist();
        }
    }

    @Override
    public User loginUser(String email, String password) throws UserAlreadyExist {
        User user = userRepository.findByEmail(email);
        if(user != null){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }else{
                 throw new UserAlreadyExist();
            }
        }
        throw new UserAlreadyExist();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser(String email)  throws UserNotFound{
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return user;
        }else{
            throw new UserNotFound();
        }
    }
}
