package com.api.usuarios.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de User Entity")
class UserTests {

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
  }

  @Test
  @DisplayName("Deve criar usuário com constructor padrão")
  void testConstructorDefault() {
    // Act
    User newUser = new User();

    // Assert
    assertNotNull(newUser);
  }

  @Test
  @DisplayName("Deve criar múltiplos usuários independentes")
  void testMultipleUserInstances() {
    // Act
    User user1 = new User();
    user1.setId(1L);
    user1.setNome("João Silva");

    User user2 = new User();
    user2.setId(2L);
    user2.setNome("Maria Santos");

    // Assert
    assertNotNull(user1);
    assertNotNull(user2);
    assertNotEquals(user1.getId(), user2.getId());
  }

  @Test
  @DisplayName("Deve definir e obter o ID do usuário")
  void testSetAndGetId() {
    // Act
    user.setId(1L);

    // Assert
    assertEquals(1L, user.getId());
  }

  @Test
  @DisplayName("Deve definir e obter o nome do usuário")
  void testSetAndGetNome() {
    // Arrange
    String nome = "João Silva";

    // Act
    user.setNome(nome);

    // Assert
    assertEquals(nome, user.getNome());
  }

  @Test
  @DisplayName("Deve definir e obter o email do usuário")
  void testSetAndGetEmail() {
    // Arrange
    String email = "joao@example.com";

    // Act
    user.setEmail(email);

    // Assert
    assertEquals(email, user.getEmail());
  }

  @Test
  @DisplayName("Deve converter usuário para string")
  void testToString() {
    // Arrange
    user.setId(1L);
    user.setNome("João Silva");
    user.setEmail("joao@example.com");

    // Act
    String result = user.toString();

    // Assert
    assertNotNull(result);
    assertTrue(result.contains("João Silva"));
    assertTrue(result.contains("joao@example.com"));
    assertTrue(result.contains("1"));
  }

  @Test
  @DisplayName("Deve validar toString com múltiplos usuários")
  void testToStringMultipleUsers() {
    // Arrange
    User user1 = new User();
    user1.setId(1L);
    user1.setNome("João");
    user1.setEmail("joao@example.com");

    User user2 = new User();
    user2.setId(2L);
    user2.setNome("Maria");
    user2.setEmail("maria@example.com");

    // Act
    String toString1 = user1.toString();
    String toString2 = user2.toString();

    // Assert
    assertNotEquals(toString1, toString2);
    assertTrue(toString1.contains("João"));
    assertTrue(toString2.contains("Maria"));
  }

  @Test
  @DisplayName("Deve manter valores após múltiplas atribuições")
  void testMultipleAssignments() {
    // Act
    user.setId(1L);
    user.setNome("João");
    user.setEmail("joao@example.com");
    user.setNome("João Silva");
    user.setEmail("joao.silva@example.com");

    // Assert
    assertEquals(1L, user.getId());
    assertEquals("João Silva", user.getNome());
    assertEquals("joao.silva@example.com", user.getEmail());
  }
}

