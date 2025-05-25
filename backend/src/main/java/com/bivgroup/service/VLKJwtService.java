package com.bivgroup.service;

import com.bivgroup.entity.Account;
import com.bivgroup.pojo.request.AuthorizationRequest;
import com.bivgroup.pojo.response.AuthorizationResponse;
import com.bivgroup.repository.AccountRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
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

    @Inject
    AccountRepository accountRepository;

    private PrivateKey privateKey;

    public VLKJwtService() throws Exception {
        // Загрузка приватного ключа из файла
        String privateKeyPEM = new String(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\BIV\\user-service\\vlk\\backend\\src\\main\\resources\\privateKey.pem")));
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(keySpec);
    }

    public AuthorizationResponse generateJwt(AuthorizationRequest request) {
        Set<String> roles = new HashSet<>();
        Account account = accountRepository.findByLogin(request.getUserLogin()).orElseThrow();
        roles.add(account.getRole());
        AuthorizationResponse response =  new AuthorizationResponse(account.getInsurer().getInsurerId(),
                Jwt.issuer("vlk-jwt")
                .subject("vlk-jwt")
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign(privateKey)
        );
        response.setStatusCode(0L);
        response.setStatusDescription("Обработан успешно");
        return response;
    }
}
