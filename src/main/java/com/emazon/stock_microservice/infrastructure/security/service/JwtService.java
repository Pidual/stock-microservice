package com.emazon.stock_microservice.infrastructure.security.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class JwtService {


    // Usamos una clave secreta más segura, codificada en base64
    private String SECRET_KEY = "7OCOuld01GpRMRt3J9KWw1hGcvKTtcbxxfvLNx8JF+g=";  // Base64 encoded key


    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        String role = claims.get("role", String.class);  // Asegúrate de que 'role' esté en el JWT

        if (role == null) {
            return Collections.emptyList();  // Devolver lista vacía si no hay rol
        }

        // Devuelve el rol con el prefijo 'ROLE_' si estás usando hasRole en el controlador
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    // Validar el token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomJwtException("Invalid JWT token");
        }
    }
}