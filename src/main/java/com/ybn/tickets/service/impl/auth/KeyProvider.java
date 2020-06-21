package com.ybn.tickets.service.impl.auth;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.ybn.tickets.config.AppConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyProvider implements RSAKeyProvider {

    @Override
    public RSAPublicKey getPublicKeyById(String s) {
        try  {
            String key = IOUtils.toString(KeyProvider.class.getClassLoader().getResourceAsStream("public.txt"), Charset.defaultCharset());
            String publicKeyPEM = key
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            // decode to get the binary DER representation
            byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyDER);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }
}
