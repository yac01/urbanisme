package com.ybn.common.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private boolean employee = false;
    private String [] groups;
}
