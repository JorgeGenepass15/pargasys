package pe.com.pargasys.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.pargasys.auth.model.dto.TokenDTO;
import pe.com.pargasys.auth.model.dto.UserDTO;
import pe.com.pargasys.auth.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/v1/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO user) {
        return new ResponseEntity<>(authService.login(user), HttpStatus.OK);
    }

    @PostMapping("/v1/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token) {
        return new ResponseEntity<>(authService.validate(token), HttpStatus.OK);
    }

}
