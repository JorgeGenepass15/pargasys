package pe.com.pargasys.auth.service;

import pe.com.pargasys.auth.model.dto.TokenDTO;
import pe.com.pargasys.auth.model.dto.UserDTO;

public interface AuthService {

    TokenDTO login(UserDTO user);
    TokenDTO validate(String token);

}
