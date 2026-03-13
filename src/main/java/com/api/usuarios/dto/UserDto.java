package com.api.usuarios.dto;

public class UserDto {
  private long id;
  private String nome;
  private String email;

  public UserDto(String nome, long id, String email) {
    this.id = id;
    this.nome = nome;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
