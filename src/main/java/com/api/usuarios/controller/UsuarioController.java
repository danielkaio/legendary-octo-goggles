package com.api.usuarios.controller;

import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import com.api.usuarios.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UserService userService;

  public UsuarioController( UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> listUsuarios() {
    return userService.listUser();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> buscar(@PathVariable Long id) {

    return userService.listId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  @DeleteMapping
  public void RemoveUserAll() {
    userService.remove();
  }
}
