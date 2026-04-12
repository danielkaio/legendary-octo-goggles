package com.api.usuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.api.usuarios.dto.UserDto;
import com.api.usuarios.entity.User;
import com.api.usuarios.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.ArgumentMatchers;

@WebMvcTest(UsuarioController.class)
@DisplayName("Testes de UsuarioController")
class UsuarioControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private UserService userService;

  @Autowired private ObjectMapper objectMapper;

  private User user;
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    user = new User(1L, "João Silva", "joao@example.com");
    userDto = new UserDto("João Silva", 1L, "joao@example.com");
  }

  @Test
  @DisplayName("GET /usuarios - Deve listar todos os usuários")
  void testListUsuarios_Success() throws Exception {
    // Arrange
    UserDto userDto2 = new UserDto("Maria Santos", 2L, "maria@example.com");
    when(userService.listUser()).thenReturn(Arrays.asList(userDto, userDto2));

    // Act & Assert
    mockMvc
        .perform(get("/usuarios").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nome").value("João Silva"))
        .andExpect(jsonPath("$[0].email").value("joao@example.com"))
        .andExpect(jsonPath("$[1].nome").value("Maria Santos"))
        .andExpect(jsonPath("$.length()").value(2));

    verify(userService, times(1)).listUser();
  }

  @Test
  @DisplayName("GET /usuarios - Deve retornar lista vazia")
  void testListUsuarios_Empty() throws Exception {
    // Arrange
    when(userService.listUser()).thenReturn(Arrays.asList());

    // Act & Assert
    mockMvc
        .perform(get("/usuarios").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));

    verify(userService, times(1)).listUser();
  }

  @Test
  @DisplayName("GET /usuarios/{id} - Deve buscar usuário por ID")
  void testBuscar_Success() throws Exception {
    // Arrange
    when(userService.listId(1L)).thenReturn(Optional.of(userDto));

    // Act & Assert
    mockMvc
        .perform(get("/usuarios/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("João Silva"))
        .andExpect(jsonPath("$.email").value("joao@example.com"))
        .andExpect(jsonPath("$.id").value(1L));

    verify(userService, times(1)).listId(1L);
  }

  @Test
  @DisplayName("GET /usuarios/{id} - Deve retornar 404 quando usuário não existe")
  void testBuscar_NotFound() throws Exception {
    // Arrange
    when(userService.listId(999L)).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc
        .perform(get("/usuarios/999").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userService, times(1)).listId(999L);
  }

  @Test
  @DisplayName("POST /usuarios - Deve criar novo usuário")
  void testCreate_Success() throws Exception {
    // Arrange
    User newUser = new User();
    newUser.setId(1L);
    newUser.setNome("João Silva");
    newUser.setEmail("joao@example.com");
    when(userService.createUser(any(User.class))).thenReturn(newUser);

    String userJson = objectMapper.writeValueAsString(newUser);

    // Act & Assert
    mockMvc
        .perform(
            post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
        .andExpect(status().isOk());

    verify(userService, times(1)).createUser(any(User.class));
  }

  @Test
  @DisplayName("PUT /usuarios/{id} - Deve atualizar usuário existente")
  void testUpdate_Success() throws Exception {
    // Arrange
    UserDto updatedDto =
        new UserDto("João Atualizado", 1L, "joao.novo@example.com");
    when(userService.updateId(eq(1L), any(UserDto.class)))
        .thenReturn(Optional.of(updatedDto));

    String dtoJson = objectMapper.writeValueAsString(updatedDto);

    // Act & Assert
    mockMvc
        .perform(
            put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("João Atualizado"))
        .andExpect(jsonPath("$.email").value("joao.novo@example.com"));

    verify(userService, times(1)).updateId(eq(1L), any(UserDto.class));
  }

  @Test
  @DisplayName("PUT /usuarios/{id} - Deve retornar 404 ao atualizar usuário inexistente")
  void testUpdate_NotFound() throws Exception {
    // Arrange
    UserDto updateDto =
        new UserDto("João Atualizado", 999L, "joao@example.com");
    when(userService.updateId(eq(999L), any(UserDto.class)))
        .thenReturn(Optional.empty());

    String dtoJson = objectMapper.writeValueAsString(updateDto);

    // Act & Assert
    mockMvc
        .perform(
            put("/usuarios/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
        .andExpect(status().isNotFound());

    verify(userService, times(1)).updateId(eq(999L), any(UserDto.class));
  }

  @Test
  @DisplayName("DELETE /usuarios/{id} - Deve deletar usuário por ID")
  void testDeletarID_Success() throws Exception {
    // Arrange
    doNothing().when(userService).deleteId(1L);

    // Act & Assert
    mockMvc
        .perform(delete("/usuarios/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(userService, times(1)).deleteId(1L);
  }

  @Test
  @DisplayName("DELETE /usuarios - Deve deletar todos os usuários")
  void testRemoveUserAll_Success() throws Exception {
    // Arrange
    doNothing().when(userService).remove();

    // Act & Assert
    mockMvc
        .perform(delete("/usuarios").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(userService, times(1)).remove();
  }
}

