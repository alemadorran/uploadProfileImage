package com.example.userProfileGenerator.repository;

import com.example.userProfileGenerator.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    // Método personalizado para encontrar la imagen por el ID del usuario
    Optional<Imagen> findByUserId(Long userId);

}