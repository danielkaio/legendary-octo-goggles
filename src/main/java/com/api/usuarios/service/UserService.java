package com.api.usuarios.service;

import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> list() {
    return userRepository.findAll();
  }

  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }

  public String ListID(@PathVariable Long Id) {

    return this.userRepository.findById(Id).toString();
  }

  public void remove() {
    this.userRepository.deleteAll();
  }
}
