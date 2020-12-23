package com.example.demo.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

		// @Query("SELECT u FROM Usuario u WHERE :login = u.login")
		Optional<Usuario> findByLogin(String login);
}
