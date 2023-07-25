package com.juanupla.tareas.JwtUtil;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "juanuplaELMata2023kuuhgfhjkndrxykfhfhnktftkntudbnktuidutdinutdniuftnouftnbofuotnftnuftnuiouftniouiftnfutniftniufniftniufntuifntuiftnuifnutyftcnujtykryhdrtrturtyuguyguk009";

    public static String generateToken(String username) {
        long expirationTime = 86400000; // Tiempo de expiración en milisegundos 1 dia

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtenemos la ruta actual de la solicitud
        String requestURI = request.getRequestURI();

        // Verificamos si la ruta es el login o la creación de usuarios
        if (requestURI.endsWith("/login") || requestURI.endsWith("/create-user")) {
            // Si es el login o la creación de usuarios, no verificamos el token y continuamos con el flujo normal
            filterChain.doFilter(request, response);
            return;
        }


        // Si no es el login o la creación de usuarios, entonces continuamos con la verificación del token
        // Obtenemos el token del encabezado "Authorization"
        String token = extractTokenFromHeader(request);

        // Realizamos la verificación del token (puedes utilizar el código de verificación del ejemplo anterior).
        boolean isTokenValid = isTokenValid(token);

        if (isTokenValid) {
            // Si el token es válido, continuamos el flujo de la solicitud hacia el controlador.
            filterChain.doFilter(request, response);
        } else {
            // Si el token no es válido, respondemos con un error de autenticación.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // Puedes agregar un mensaje de error personalizado en la respuesta.
            response.getWriter().write("Token inválido o caducado");
        }
    }

    // ... (otros métodos del filtro)


    private String extractTokenFromHeader(HttpServletRequest request) {
        // Obtenemos el valor completo del encabezado "Authorization"
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extraemos el token eliminando el prefijo "Bearer "
            return authorizationHeader.substring(7);
        }
        return null;
    }


    private boolean isTokenValid(String token) {

        try {
            if (token != null) {
                // Verificar el token usando la clave secreta
                Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                        .build()
                        .parseClaimsJws(token);

                // Si no ocurre una excepción, el token es válido


                // Por ejemplo, para verificar si el token ha expirado:
                Date expirationDate = claimsJws.getBody().getExpiration();
                Date now = new Date();
                if (expirationDate != null && expirationDate.before(now)) {
                    return false;
                }
                return true;
            }

        } catch (JwtException e) {
            // Si ocurre una excepción al verificar el token, el token no es válido
            return false;
        }
        return false;
    }

}
