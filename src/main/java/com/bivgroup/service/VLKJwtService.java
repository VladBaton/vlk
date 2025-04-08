package com.bivgroup.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class VLKJwtService {

    private PrivateKey privateKey;

    public VLKJwtService() throws Exception {
        // Загрузка приватного ключа из файла
        String privateKeyPEM = new String(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\BIV\\user-service\\vlk\\src\\main\\resources\\privateKey.pem")));
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(keySpec);
    }

    public String generateJwt() {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        return Jwt.issuer("vlk-jwt")
                .subject("vlk-jwt")
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign(privateKey);
    }
}
