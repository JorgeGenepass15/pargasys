package pe.com.pargasys.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.com.pargasys.auth.constant.Constant;
import pe.com.pargasys.auth.exception.UnauthorizedException;
import pe.com.pargasys.auth.model.entity.UserEntity;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.exp}")
    private String exp;

    public String createToken(UserEntity user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        Date now = new Date();
        log.info("exp: {}", exp);
        Date expiration = new Date(now.getTime() + Long.parseLong(exp) * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public void validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(Constant.U_INVALID_LOGIN);
        }

    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(Constant.U_INVALID_LOGIN);
        }
    }

}
