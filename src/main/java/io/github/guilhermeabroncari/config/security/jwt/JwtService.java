package io.github.guilhermeabroncari.config.security.jwt;

import io.github.guilhermeabroncari.domain.entity.UserLogin;
import io.github.guilhermeabroncari.domain.repository.UserLoginRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private String expirationToken;
    @Value("${security.jwt.subscription-key}")
    private String subscriptionKey;
    private final UserLoginRepository userLoginRepository;

    public JwtService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public String tokenGenerator(UserLogin userLogin) {
        long expString = Long.valueOf(expirationToken);
        LocalDateTime dataHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts.builder()
                .setSubject(userLogin.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, subscriptionKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(subscriptionKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date experationDate = claims.getExpiration();
            LocalDateTime localDateTime = experationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }
}
