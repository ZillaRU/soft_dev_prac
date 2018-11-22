package com.len.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JWTUtil {

    /**
     * 过期时间3小时
     */
    private static final long EXPIRE_TIME = 3 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取当前用户
     *
     * @param token jwt加密信息
     * @return 解析的当前用户信息
     */
    public static Principal getPrincipal(String token) {
        try {
            Principal principal = new Principal();
            DecodedJWT jwt = JWT.decode(token);
            principal.setUserId(jwt.getClaim("userId").asString());
            principal.setUserName(jwt.getClaim("username").asString());
            String[] roleArr = jwt.getClaim("roles").asArray(String.class);
            if (roleArr != null) {
                principal.setRoles(Arrays.asList(roleArr));
            }
            return principal;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取角色组
     *
     * @param token
     * @return
     */
    public static String[] getRoles(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("roles").asArray(String.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param username 用户名
     * @param userId   用户id
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String userId, List<String> roles, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String[] roleArr = new String[roles.size()];
        roleArr = roles.toArray(roleArr);
        // 附带username信息
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withArrayClaim("roles", roleArr)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}