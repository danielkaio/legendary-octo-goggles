package com.api.usuarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.api.usuarios.dto.UserDto;
import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes de UserService")
class UserServiceTests {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  private User user;
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setId(1L);
    user.setNome("João Silva");
    user.setEmail("joao@example.com");
    userDto = new UserDto(1L, "daniel", "dani.ajala@yahoo.com");
  }

  @Test
  @DisplayName("Deve listar todos os usuários com sucesso")
  void testListUser_Success() {
    // Arrange
    User user2 = new User();
    user2.setId(2L);
    user2.setNome("Maria Santos");
    user2.setEmail("maria@example.com");
    when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));

    // Act
    List<UserDto> result = userService.listUser();

    // Assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("João Silva", result.get(0).nome());
    assertEquals("maria@example.com", result.get(1).email());
    verify(userRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Deve retornar lista vazia quando não há usuários")
  void testListUser_Empty() {
    // Arrange
    when(userRepository.findAll()).thenReturn(Arrays.asList());

    // Act
    List<UserDto> result = userService.listUser();

    // Assert
    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(userRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Deve criar um usuário com sucesso")
  void testCreateUser_Success() {
    // Arrange
    User newUser = new User();
    newUser.setNome("João Silva");
    newUser.setEmail("joao@example.com");
    User savedUser = new User();
    savedUser.setId(1L);
    savedUser.setNome("João Silva");
    savedUser.setEmail("joao@example.com");
    when(userRepository.save(any(User.class))).thenReturn(savedUser);

    // Act
    User result = userService.createUser(newUser);

    // Assert
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("João Silva", result.getNome());
    assertEquals("joao@example.com", result.getEmail());
    verify(userRepository, times(1)).save(newUser);
  }

  @Test
  @DisplayName("Deve buscar usuário por ID com sucesso")
  void testListId_Success() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    // Act
    Optional<UserDto> result = userService.listId(1L);

    // Assert
    assertTrue(result.isPresent());
    assertEquals("João Silva", result.get().nome());
    assertEquals(1L, result.get().id());
    assertEquals("joao@example.com", result.get().email());
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("Deve retornar Optional vazio quando usuário não existe")
  void testListId_NotFound() {
    // Arrange
    when(userRepository.findById(999L)).thenReturn(Optional.empty());

    // Act
    Optional<UserDto> result = userService.listId(999L);

    // Assert
    assertTrue(result.isEmpty());
    verify(userRepository, times(1)).findById(999L);
  }

  @Test
  @DisplayName("Deve atualizar usuário com sucesso")
  void testUpdateId_Success() {
    // Arrange
    UserDto updateDto = new UserDto(1L, "João Atualizado", "joao.novo@example.com");
    User existingUser = new User();
    existingUser.setId(1L);
    existingUser.setNome("João Silva");
    existingUser.setEmail("joao@example.com");

    User updatedUser = new User();
    updatedUser.setId(1L);
    updatedUser.setNome("João Atualizado");
    updatedUser.setEmail("joao.novo@example.com");

    when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    // Act
    Optional<UserDto> result = userService.updateId(1L, updateDto);

    // Assert
    assertTrue(result.isPresent());
    assertEquals("João Atualizado", result.get().nome());
    assertEquals("joao.novo@example.com", result.get().email());
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("Deve retornar Optional vazio ao atualizar usuário que não existe")
  void testUpdateId_NotFound() {
    // Arrange
    when(userRepository.findById(999L)).thenReturn(Optional.empty());

    // Act
    Optional<UserDto> result = userService.updateId(999L, userDto);

    // Assert
    assertTrue(result.isEmpty());
    verify(userRepository, times(1)).findById(999L);
    verify(userRepository, never()).save(any(User.class));
  }

  @Test
  @DisplayName("Deve deletar usuário por ID com sucesso")
  void testDeleteId_Success() {
    // Arrange
    doNothing().when(userRepository).deleteById(1L);

    // Act
    userService.deleteId(1L);

    // Assert
    verify(userRepository, times(1)).deleteById(1L);
  }

  @Test
  @DisplayName("Deve deletar todos os usuários com sucesso")
  void testRemove_Success() {
    // Arrange
    doNothing().when(userRepository).deleteAll();

    // Act
    userService.remove();

    // Assert
    verify(userRepository, times(1)).deleteAll();
  }
}

