package org.ares.app.demo.service;

import java.util.List;

import org.ares.app.demo.model.UserModel;

public interface IUserService {
  
    void addUser(UserModel user);  
  
    UserModel getUser(String userId);  
  
    void updateUser(UserModel user);  
  
    void deleteUser(String userId);  
    
    List<?> getUsers();

}
