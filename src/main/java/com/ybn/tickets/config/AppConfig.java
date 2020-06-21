package com.ybn.tickets.config;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

@Data
@ConfigurationProperties("app")
public class AppConfig {
    private String clientId;
    private String clientSecret;
    private int accessTokenValidityInSeconds;
    private int refreshTokenValiditySeconds;
    private String publicKey;
    private String privateKey;
    private String runningOn;
    private String[] unsecuredPath;
    @PostConstruct
    public void loadKeys() {
        try {
            this.publicKey = this.load("public.txt");
            this.privateKey = this.load("private.txt");
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }
    private String load(String res) throws IOException {
        return IOUtils.toString(AppConfig.class.getClassLoader().getResource(res), Charset.defaultCharset());
    }
}
