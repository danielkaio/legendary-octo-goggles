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
    userDto = new UserDto("João Silva", 1L, "joao@example.com");
  }

  @Test
  @DisplayName("Deve criar UserDto com constructor parametrizado")
  void testConstructorWithParameters() {
    // Arrange & Act
    UserDto dto = new UserDto("João Silva", 1L, "joao@example.com");

    // Assert
    assertNotNull(dto);
    assertEquals("João Silva", dto.getNome());
    assertEquals(1L, dto.getId());
    assertEquals("joao@example.com", dto.getEmail());
  }

  @Test
  @DisplayName("Deve definir e obter o ID do DTO")
  void testSetAndGetId() {
    // Act
    userDto.setId(5L);

    // Assert
    assertEquals(5L, userDto.getId());
  }

  @Test
  @DisplayName("Deve definir e obter o nome do DTO")
  void testSetAndGetNome() {
    // Arrange
    String novaNome = "Maria Santos";

    // Act
    userDto.setNome(novaNome);

    // Assert
    assertEquals(novaNome, userDto.getNome());
  }

  @Test
  @DisplayName("Deve definir e obter o email do DTO")
  void testSetAndGetEmail() {
    // Arrange
    String novoEmail = "joao.novo@example.com";

    // Act
    userDto.setEmail(novoEmail);

    // Assert
    assertEquals(novoEmail, userDto.getEmail());
  }

  @Test
  @DisplayName("Deve validar valores do DTO após inicialização")
  void testInitialValues() {
    // Assert
    assertEquals("João Silva", userDto.getNome());
    assertEquals(1L, userDto.getId());
    assertEquals("joao@example.com", userDto.getEmail());
  }

  @Test
  @DisplayName("Deve manter valores após múltiplas atribuições")
  void testMultipleAssignments() {
    // Act
    userDto.setId(10L);
    userDto.setNome("Paulo");
    userDto.setEmail("paulo@example.com");
    userDto.setNome("Paulo Silva");

    // Assert
    assertEquals(10L, userDto.getId());
    assertEquals("Paulo Silva", userDto.getNome());
    assertEquals("paulo@example.com", userDto.getEmail());
  }

  @Test
  @DisplayName("Deve criar múltiplos DTOs com dados diferentes")
  void testMultipleDtos() {
    // Arrange & Act
    UserDto dto1 = new UserDto("João", 1L, "joao@example.com");
    UserDto dto2 = new UserDto("Maria", 2L, "maria@example.com");

    // Assert
    assertNotEquals(dto1.getId(), dto2.getId());
    assertNotEquals(dto1.getNome(), dto2.getNome());
    assertNotEquals(dto1.getEmail(), dto2.getEmail());
  }

  @Test
  @DisplayName("Deve permitir atualização de todos os campos")
  void testUpdateAllFields() {
    // Act
    userDto.setId(100L);
    userDto.setNome("Novo Nome");
    userDto.setEmail("novo@example.com");

    // Assert
    assertEquals(100L, userDto.getId());
    assertEquals("Novo Nome", userDto.getNome());
    assertEquals("novo@example.com", userDto.getEmail());
  }

  @Test
  @DisplayName("Deve aceitar null em campos do DTO")
  void testNullValues() {
    // Act
    userDto.setNome(null);
    userDto.setEmail(null);

    // Assert
    assertNull(userDto.getNome());
    assertNull(userDto.getEmail());
    assertEquals(1L, userDto.getId());
  }

  @Test
  @DisplayName("Deve criar DTO com ID zero")
  void testZeroId() {
    // Arrange & Act
    UserDto dto = new UserDto("João", 0L, "joao@example.com");

    // Assert
    assertEquals(0L, dto.getId());
  }

  @Test
  @DisplayName("Deve criar DTO com email vazio")
  void testEmptyEmail() {
    // Arrange & Act
    UserDto dto = new UserDto("João", 1L, "");

    // Assert
    assertEquals("", dto.getEmail());
  }

  @Test
  @DisplayName("Deve criar DTO com nome vazio")
  void testEmptyNome() {
    // Arrange & Act
    UserDto dto = new UserDto("", 1L, "joao@example.com");

    // Assert
    assertEquals("", dto.getNome());
  }
}

