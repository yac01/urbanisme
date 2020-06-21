package com.ybn.tickets.collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority {
    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
