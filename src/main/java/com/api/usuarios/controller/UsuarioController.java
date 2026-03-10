package com.api.usuarios.controller;

import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/{id}")
  public ResponseEntity<User> buscar(@PathVariable Long id) {

    return userRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }

  @DeleteMapping
  public void RemoveUserAll() {
    userRepository.deleteAll();
  }
}
