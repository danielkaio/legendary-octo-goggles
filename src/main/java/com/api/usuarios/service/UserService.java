package com.api.usuarios.service;

import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> listUser() {
    return userRepository.findAll();
  }

  public User create(User user) {
    return userRepository.save(user);
  }

  public Optional<User> listId(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> updateId(Long id, User user) {

    Optional<User> existingUser = userRepository.findById(id);

    if (existingUser.isPresent()) {

      User u = existingUser.get();
      u.setNome(user.getNome());
      u.setEmail(user.getEmail());

      return Optional.of(userRepository.save(u));
    }

    return Optional.empty();
  }

  public void remove() {
    userRepository.deleteAll();
  }
}
