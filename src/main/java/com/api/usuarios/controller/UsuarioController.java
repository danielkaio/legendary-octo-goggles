package com.api.usuarios.controller;

import com.api.usuarios.dto.UserDto;
import com.api.usuarios.entity.User;
import com.api.usuarios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

  private final UserService userService;

  public UsuarioController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  @Operation(summary = "Listar usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
  public List<UserDto> listUsuarios() {
    return userService.listUser();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Buscar usuário por ID", description = "Retorna os detalhes de um usuário específico pelo ID.")
  public ResponseEntity<UserDto> buscar(@PathVariable Long id) {

    return userService.listId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @Operation(summary = "Criar usuário", description = "Cria um novo usuário com os dados fornecidos.")
  public User create(@RequestBody User UserDto) {
    return userService.createUser(UserDto);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente pelo ID.")
  public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {

    return userService
        .updateId(id, dto)
        .map(ResponseEntity::ok)
        .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletar usuário por ID", description = "Remove um usuário específico pelo ID.")
  public ResponseEntity<UserDto> DeletarID(@PathVariable Long id) {

    userService.deleteId(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  @Operation(summary = "Remover todos os usuários", description = "Remove todos os usuários cadastrados.")
  public void RemoveUserAll() {
    userService.remove();
  }
}
