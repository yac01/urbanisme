package com.ybn.urban.service;


import com.ybn.common.collection.TicketUser;
import com.ybn.common.dto.UserDto;
import com.ybn.urban.rest.technical.RestPage;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    String create(UserDto udto);

    RestPage<TicketUser> getAllUsers(int limit, int offset);

    void updateRole(String mode, String roleName, String username);
}
