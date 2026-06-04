package com.api.usuarios.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de UserDto")
class UserDtoTests {

  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto(1L, "João Silva", "joao@example.com");
  }

  @Test
  @DisplayName("Deve criar UserDto com constructor parametrizado")
  void testConstructorWithParameters() {
    // Arrange & Act
    UserDto dto = new UserDto(1L, "João Silva", "joao@example.com");

    // Assert
    assertNotNull(dto);
    assertEquals(1L, dto.id());
    assertEquals("João Silva", dto.nome());
    assertEquals("joao@example.com", dto.email());
  }

  @Test
  @DisplayName("Deve validar valores do DTO após inicialização")
  void testInitialValues() {
    // Assert
    assertEquals(1L, userDto.id());
    assertEquals("João Silva", userDto.nome());
    assertEquals("joao@example.com", userDto.email());
  }

  @Test
  @DisplayName("Deve criar múltiplos DTOs com dados diferentes")
  void testMultipleDtos() {
    // Arrange & Act
    UserDto dto1 = new UserDto(1L, "João", "joao@example.com");
    UserDto dto2 = new UserDto(2L, "Maria", "maria@example.com");

    // Assert
    assertNotEquals(dto1.id(), dto2.id());
    assertNotEquals(dto1.nome(), dto2.nome());
    assertNotEquals(dto1.email(), dto2.email());
  }

  @Test
  @DisplayName("Deve aceitar null em campos do DTO")
  void testNullValues() {
    // Act
    UserDto dto = new UserDto(1L, null, null);

    // Assert
    assertEquals(1L, dto.id());
    assertNull(dto.nome());
    assertNull(dto.email());
  }

  @Test
  @DisplayName("Deve criar DTO com ID zero")
  void testZeroId() {
    // Arrange & Act
    UserDto dto = new UserDto(0L, "João", "joao@example.com");

    // Assert
    assertEquals(0L, dto.id());
  }

  @Test
  @DisplayName("Deve criar DTO com email vazio")
  void testEmptyEmail() {
    // Arrange & Act
    UserDto dto = new UserDto(1L, "João", "");

    // Assert
    assertEquals("", dto.email());
  }

  @Test
  @DisplayName("Deve criar DTO com nome vazio")
  void testEmptyNome() {
    // Arrange & Act
    UserDto dto = new UserDto(1L, "", "joao@example.com");

    // Assert
    assertEquals("", dto.nome());
  }

  @Test
  @DisplayName("Records com mesmo conteúdo devem ser iguais")
  void testEqualsAndHashCode() {
    UserDto dto1 = new UserDto(1L, "João", "joao@example.com");
    UserDto dto2 = new UserDto(1L, "João", "joao@example.com");

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }
}

