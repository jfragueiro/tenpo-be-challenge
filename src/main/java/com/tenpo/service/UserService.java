package com.tenpo.service;

import com.tenpo.exceptions.AuthException;
import com.tenpo.exceptions.BadRequestException;
import com.tenpo.model.Token;
import com.tenpo.model.UserResource;
import com.tenpo.persistence.UserEntity;
import com.tenpo.persistence.UserRepository;
import com.tenpo.utils.Constants;
import com.tenpo.utils.UserParser;
import com.tenpo.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements com.tenpo.model.User {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResource save(final UserResource userResource) {
        if (userResource.getEmail() == null || userResource.getEmail().isEmpty() ||
            userResource.getPassword() == null|| userResource.getPassword().isEmpty()) {
            throw new BadRequestException("Invalid email or Password");
        }
        if (!Utils.validateEmail(userResource.getEmail())) {
            throw new BadRequestException("Invalid email or Password");
        }
        if (!userExist(userResource.getEmail())) {
            final UserEntity entity = userRepository.save(UserParser.parse(userResource));
            return UserParser.parse(entity);
        } else {
            throw new BadRequestException("User already taken with email. Please use another.");
        }

    }

    public Token login(UserResource userResource) {
        return new Token(validateUser(userResource));
    }

    @Override
    public boolean userExist(String email) throws RuntimeException {
        return userRepository.findByEmail(email) != null ? true : false;
    }

    @Override
    public String validateUser(UserResource userResource) throws RuntimeException {

        if (userResource.getEmail() == null || userResource.getEmail().isEmpty() ||
                userResource.getPassword() == null || userResource.getPassword().isEmpty()) {
            throw new BadRequestException("Email or Password are empty");
        }
        if (!userExist(userResource.getEmail())) {
            throw new AuthException("Email is not registered");
        }
        UserResource logUserResource = UserParser.parse(userRepository.findByEmail(userResource.getEmail()));
        if (userResource.getPassword().equals(logUserResource.getPassword())) {
            return generateJWT(userResource.getEmail());
        } else {
            throw new AuthException("Email or password does not match");
        }
    }

    private String generateJWT(final String email) {
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("email", email)
                .compact();

        return token;
    }

    public boolean validateJwt(final String authHeader) {
        if (authHeader != null) {

            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                try {
                    String token = authHeaderArr[1];
                    Claims claims = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
                    return true;
                } catch (Exception e) {
                    throw new AuthException("Invalid Authorization Header");
                }
            } else {
                throw new BadRequestException("Invalid Authorization Header. Desired format is 'Bearer token'");
            }
        } else {
            throw new AuthException("Authorization Header is missing");
        }
    }

}
