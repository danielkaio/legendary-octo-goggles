package com.api.usuarios.controller;

import com.api.usuarios.Entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UserRepository userRepository;

  public UsuarioController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<User> listUsuarios() {
    return userRepository.findAll();
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }
}
