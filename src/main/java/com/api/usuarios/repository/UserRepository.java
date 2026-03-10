package com.api.usuarios.repository;

import com.api.usuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
