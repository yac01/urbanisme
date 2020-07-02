package com.ybn.urban.service.impl;

import com.ybn.common.collection.Authority;
import com.ybn.common.collection.Group;
import com.ybn.common.collection.TicketUser;
import com.ybn.common.dto.UserDto;
import com.ybn.common.repository.AuthorityRepository;
import com.ybn.common.repository.UserRepository;
import com.ybn.common.repository.custom.GroupRepository;
import com.ybn.urban.rest.exception.ExceptionKeyCode;
import com.ybn.urban.rest.exception.TicketException;
import com.ybn.urban.rest.technical.RestPage;
import com.ybn.urban.service.IUserService;
import com.ybn.urban.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private GroupRepository groupRepository;


    @Override
    public String create(UserDto udto) {
        this.validateMandatoryFields(udto);
        this.validateUnicity(udto);
        TicketUser user = new TicketUser();
        user.setUsername(udto.getUsername());
        user.setEmail(udto.getEmail());
        user.setPassword(this.encoder.encode(udto.getPassword()));
        user.setActiveAccount(true);
        if (udto.isAdmin()) {
            Optional<Authority> adminRole = this.authorityRepository.findByRole("admin");
            if (adminRole.isPresent()) {
                this.handleRoleUpdate(adminRole.get(), user, "ADD");
            }
        }
        if (udto.isEmployee()) {
            Optional<Authority> employeeRole = this.authorityRepository.findByRole("employee");
            if (employeeRole.isPresent()) {
                this.handleRoleUpdate(employeeRole.get(), user, "ADD");
            }
        }
        if (udto.getGroups() != null && udto.getGroups().length > 0) {
            Collection<Group> groups = this.groupRepository.findByNameIn(udto.getGroups());
            user.setGroups(groups);
        }
        this.userRepository.save(user);
        return "utilisateur sauvegardé implémenter la redirection";
    }
    @Override
    public RestPage<TicketUser> getAllUsers(int limit, int offset) {
        Page<TicketUser> page = this.userRepository.findAll(PageRequest.of(offset, limit));
        return RestPage.from(page);
    }

    @Override
    public void updateRole(String mode, String roleName, String username) {
        Optional<Authority> opr = this.authorityRepository.findByRole(roleName);
        Optional<TicketUser> opu = this.userRepository.findByUsername(username);
        if (!opr.isPresent() || !opu.isPresent()) {
            throw new TicketException(ExceptionKeyCode.E_G_0001);
        }
        var user = opu.get();
        if (user.getAuthorities() == null) {
            user.setAuthorities(new ArrayList<>());
        }
        this.handleRoleUpdate(opr.get(), user, mode);
        this.userRepository.save(user);
    }

    private void handleRoleUpdate(Authority authority, TicketUser user, String mode) {

        if ("ADD".equals(mode)) {
            if (!user.getAuthorities().stream().anyMatch(a -> a.getRole().equals(authority.getRole())))
              user.getAuthorities().add(authority);
        } else {
            if (user.getAuthorities().stream().anyMatch(a -> a.getRole().equals(authority.getRole())))
                user.getAuthorities().remove(authority);
        }
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
