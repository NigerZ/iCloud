package com.ohh.icloud.utils;


import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtUtil {

    private static Long EXPIRE_SECONDS = 3600L;

    private static String SECRET = "iCloud_secret";

    /**
     * 生成token
     *
     * @param id 用户id
     * @param username 用户账号
     * @return 生成的token
     */
    public static String generateToken(Long id, String username) {
        return Jwts.builder()
                .setSubject("AUTH-USER")
                .claim("userId", id)
                .claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_SECONDS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析token获取用户id
     * @param token 令牌
     * @return 用户id
     */
    public static Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Object userId = body.get("userId");
            return Long.parseLong(userId.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析token获取用户账号
     * @param token 令牌
     * @return 用户账号
     */
    public static String getUsername(String token) {
        if (StringUtils.isEmpty(token)) return null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Object username = body.get("username");
            return username.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
