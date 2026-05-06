package com.api.usuarios.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)  // ← adicionar esta linha

  private Long id;

  private String nome;

  private String email;

  public User() {}

  public User(long id, String nome, String email) {}

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", nome='" + nome + '\'' + ", email='" + email + '\'' + '}';
  }
}
