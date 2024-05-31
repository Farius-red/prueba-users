package com.juliaosystem.api.controller;

import com.juliaosystem.api.dtos.request.RegisterUserDTO;
import com.juliaosystem.infrastructure.services.primary.UserService;
import com.juliaosystem.utils.PlantillaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "usuarios", description = "Endpoints relacionados con el manejo de usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @Operation(summary = "add", description = "Permite agregar un usuario")
    @PostMapping("/add")
    public ResponseEntity<PlantillaResponse<RegisterUserDTO>> add(
            @RequestBody(required = true) RegisterUserDTO entidad) {
        var response = userService.addUser(entidad);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation(summary = "all users", description = "permite obtener lista de usuarios ")
    @GetMapping("/all")
    public ResponseEntity<PlantillaResponse<RegisterUserDTO>> all(
            @RequestParam(value = "id", required = false) UUID id,
            @RequestHeader("idBussines") Long idBussines) {
        var response = userService.getUsers(id, idBussines);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
