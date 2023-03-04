package pe.com.pargasys.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.com.pargasys.auth.constant.Constant;
import pe.com.pargasys.auth.exception.UnauthorizedException;

@Component
public class Util {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void matchesPassword(String pwdD, String pwdE) {
        if (!passwordEncoder.matches(pwdD, pwdE)) {
            throw new UnauthorizedException(Constant.U_INVALID_LOGIN);
        }
    }

}
