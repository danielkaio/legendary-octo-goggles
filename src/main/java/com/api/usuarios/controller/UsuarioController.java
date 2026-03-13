package com.api.usuarios.controller;

import com.api.usuarios.dto.UserDto;
import com.api.usuarios.entity.User;
import com.api.usuarios.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UserService userService;

  public UsuarioController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> listUsuarios() {
    return userService.listUser();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> buscar(@PathVariable Long id) {

    return userService.listId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public User create(@RequestBody User UserDto) {
    return userService.createUser(UserDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {

    return userService
        .updateId(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public void RemoveUserAll() {
    userService.remove();
  }
}
