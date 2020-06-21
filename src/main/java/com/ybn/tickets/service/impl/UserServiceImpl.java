package com.ybn.tickets.service.impl;

import com.ybn.tickets.collection.TicketUser;
import com.ybn.tickets.repository.UserRepository;
import com.ybn.tickets.rest.dto.UserDto;
import com.ybn.tickets.rest.exception.ExceptionKeyCode;
import com.ybn.tickets.service.IUserService;
import com.ybn.tickets.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public String create(UserDto udto) {
        this.validateMandatoryFields(udto);
        this.validateUnicity(udto);
        TicketUser user = new TicketUser();
        user.setUsername(udto.getUsername());
        user.setEmail(udto.getEmail());
        user.setPassword(this.encoder.encode(udto.getPassword()));
        user.setActiveAccount(true);
        this.userRepository.save(user);
        return "utilisateur sauvegardé implémenter la redirection";
    }

    private void validateMandatoryFields(UserDto udto) {
        Validator.validateNotEmpty(udto.getUsername(), ExceptionKeyCode.E_G_0002, "username");
        Validator.validateNotEmpty(udto.getPassword(), ExceptionKeyCode.E_G_0002, "password");
        Validator.validateNotEmpty(udto.getEmail(), ExceptionKeyCode.E_G_0002, "email");
    }

    private void validateUnicity(UserDto userDto) {
        Validator.throwIfNotTrue(this.userRepository.isUnique("username", userDto.getUsername()), "username", ExceptionKeyCode.E_G_0003);
        Validator.throwIfNotTrue(this.userRepository.isUnique("email", userDto.getEmail()), "email", ExceptionKeyCode.E_G_0003);

    }
}
