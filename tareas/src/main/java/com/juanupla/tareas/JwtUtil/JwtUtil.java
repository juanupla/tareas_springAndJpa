package com.juanupla.tareas.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "juanuplaELMata2023kuuhgfhjkndrxykfhfhnktftkntudbnktuidutdinutdniuftnouftnbofuotnftnuftnuiouftniouiftnfutniftniufnifnitunftuiftniftniufntuifntuiftnuifnutyftcnujtykryhdrtrturtyuguyguk009"; // Clave secreta para firmar el token

    // Método para crear un token JWT
    public static String generateToken(String username) {
        long expirationTime = 86400000; // Tiempo de expiración en milisegundos (1 día)

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Método para verificar y obtener el nombre de usuario desde un token JWT
    public static String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {

            return null;
        }
    }

}
