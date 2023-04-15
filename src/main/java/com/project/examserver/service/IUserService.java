package com.project.examserver.service;

import com.project.examserver.domain.User;
import com.project.examserver.exception.UserAlreadyExist;
import com.project.examserver.exception.UserNotFound;
import com.project.examserver.repository.UserRepository;

import java.util.List;

public interface IUserService {

    User registerUser(User user) throws UserAlreadyExist;

    User loginUser(String email, String password) throws UserAlreadyExist;

    List<User> getAllUser();

    User getCurrentUser(String email) throws UserNotFound;


}
