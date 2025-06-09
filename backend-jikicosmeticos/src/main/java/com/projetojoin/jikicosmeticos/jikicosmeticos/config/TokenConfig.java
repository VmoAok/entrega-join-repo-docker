/* token service permite a geração e validação de tokens JWT
    * para autenticação de usuários no sistema.
 */

package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenConfig {
    private static final Logger logger = LoggerFactory.getLogger(TokenConfig.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("internojiki")
                    .withSubject(String.valueOf(usuario.getIdUser()))
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            logger.info("Token JWT Gerado: {}", token);
            return token;

        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("internojiki")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}