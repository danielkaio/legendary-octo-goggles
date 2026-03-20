package com.api.usuarios.service;

import com.api.usuarios.dto.UserDto;
import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public List<UserDto> listUser() {
    return userRepository.findAll().stream()
        .map(user -> new UserDto(user.getNome(), user.getId(), user.getEmail()))
        .toList();
  }

  public User createUser(User UserDto) {
    return userRepository.save(UserDto);
  }

  public Optional<UserDto> listId(Long id) {
    return userRepository
        .findById(id)
        .map(user -> new UserDto(user.getNome(), user.getId(), user.getEmail()));
  }

  @Transactional
  public void deleteId(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional(readOnly = false)
  public Optional<UserDto> updateId(Long id, UserDto dto) {

    return userRepository
        .findById(id)
        .map(
            user -> {
              user.setNome(dto.getNome());
              user.setEmail(dto.getEmail());

              User saved = userRepository.save(user);

              return new UserDto(saved.getNome(), saved.getId(), saved.getEmail());
            });
  }

  public void remove() {
    userRepository.deleteAll();
  }
}
