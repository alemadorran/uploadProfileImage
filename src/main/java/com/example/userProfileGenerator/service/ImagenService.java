package com.example.userProfileGenerator.service;

import com.example.userProfileGenerator.model.Imagen;
import com.example.userProfileGenerator.model.User;
import com.example.userProfileGenerator.repository.ImagenRepository;
import com.example.userProfileGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private UserRepository userRepository;

    public void guardarAvatar(Long usuarioId, MultipartFile file) throws IOException {

        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Imagen imagen = imagenRepository.findByUserId(usuarioId)
                .orElse(new Imagen());

        imagen.setUser(usuario);
        imagen.setDatos(file.getBytes());
        imagenRepository.save(imagen);
    }

    public void eliminarAvatar(Long usuarioId) {

        System.out.println(
             "Imagenes borradas: " + imagenRepository.deleteByUserId(usuarioId)
        );
    }
}
