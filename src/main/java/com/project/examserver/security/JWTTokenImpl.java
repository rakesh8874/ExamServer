package com.project.examserver.security;

import com.project.examserver.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenImpl implements IGenerateJwt{
    @Override
    public Map<String, String> getJwtToken(User user) {
        String jwtTocken;
        jwtTocken = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "userKey").compact();
        Map<String, String> map = new HashMap<>();
        map.put("token",jwtTocken);
        map.put("message","User logged In Successfully");
        return map;
    }
}
