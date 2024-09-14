package com.emazon.stock_microservice.infrastructure.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.List;

@Service
public class JwtService {

    // Usamos una clave secreta más segura, codificada en base64
    private final String SECRET_KEY = "7OCOuld01GpRMRt3J9KWw1hGcvKTtcbxxfvLNx8JF+g=";  // Base64 encoded key

    // Método para obtener la clave de firma desde la cadena Base64
    private Key getSigningKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(SECRET_KEY);  // Decodifica la clave en Base64
        return Keys.hmacShaKeyFor(keyBytes);  // Usa la clave decodificada para HMAC-SHA256
    }

    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()  // Usa parserBuilder() en lugar de parser()
                .setSigningKey(getSigningKey())  // Método que obtiene la clave secreta correctamente
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Extrae el 'subject' (normalmente el username)
    }

    // Extraer roles/authorities del token JWT
    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()  // Usar parserBuilder en lugar de parser()
                .setSigningKey(getSigningKey())  // Método que obtiene la clave secreta correctamente
                .build()
                .parseClaimsJws(token)
                .getBody();

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
            Jwts.parserBuilder()  // Usar parserBuilder en lugar de parser()
                    .setSigningKey(getSigningKey())  // Usar clave de firma adecuada
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomJwtException("Invalid JWT token");
        }
    }
}
