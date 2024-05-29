package com.example.userProfileGenerator.controller;
import com.example.userProfileGenerator.model.User;
import com.example.userProfileGenerator.repository.ImagenRepository;
import com.example.userProfileGenerator.model.Imagen;
import com.example.userProfileGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "http://localhost:4200")
public class ImagenController {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{usuarioId}")
    public Imagen uploadAvatar(@PathVariable Long usuarioId, @RequestParam("file") MultipartFile file) {
        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        try {
            Imagen imagen = new Imagen();
            imagen.setDatos(file.getBytes());
            imagen.setUser(usuario);

            return imagenRepository.save(imagen);
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo guardar el archivo. ¡Por favor, inténtelo de nuevo!", ex);
        }
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long usuarioId) {
        //Optional<Imagen> imagen = imagenRepository.findByUserId(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Optional<Imagen> imagen = imagenRepository.findByUserId(usuarioId);

        if (imagen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(imagen.get().getDatos());

        }

    }
}

