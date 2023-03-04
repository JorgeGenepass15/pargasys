package pe.com.pargasys.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.pargasys.auth.constant.Constant;
import pe.com.pargasys.auth.exception.UnauthorizedException;
import pe.com.pargasys.auth.model.dto.TokenDTO;
import pe.com.pargasys.auth.model.dto.UserDTO;
import pe.com.pargasys.auth.model.entity.UserEntity;
import pe.com.pargasys.auth.repository.UserRepository;
import pe.com.pargasys.auth.service.AuthService;
import pe.com.pargasys.auth.util.JwtUtil;
import pe.com.pargasys.auth.util.Util;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Util util;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TokenDTO login(UserDTO user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UnauthorizedException(Constant.U_INVALID_LOGIN));
        util.matchesPassword(user.getPassword(), userEntity.getPassword());
        return new TokenDTO(jwtUtil.createToken(userEntity));
    }

    @Override
    public TokenDTO validate(String token) {
        jwtUtil.validate(token);
        String username = jwtUtil.getUsernameFromToken(token);
        userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException(Constant.U_INVALID_LOGIN));
        return new TokenDTO(token);
    }

}
