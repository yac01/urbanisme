package com.ybn.resourceserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ybn.resourceserver.service.UserDetailsServiceImpl.CustomUser;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object o = authentication.getPrincipal();
        if (o instanceof CustomUser) {
            CustomUser cu = (CustomUser) o;
            Map<String, Object> extras = new HashMap<>();
            extras.put("groups", cu.getGroups());
            extras.put("email", cu.getEmail());
            if (cu.getAuthorities() != null && cu.getAuthorities().size() > 0) {
                extras.put("authorities", cu.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(extras);
        }
        return accessToken;
    }
}
