package com.api.usuarios;

import com.api.usuarios.Entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> List() {
    return this.userRepository.findAll();
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }
}
