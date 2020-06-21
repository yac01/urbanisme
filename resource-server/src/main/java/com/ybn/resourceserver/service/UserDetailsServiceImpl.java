package com.ybn.resourceserver.service;

import com.ybn.common.collection.Authority;
import com.ybn.common.collection.TicketUser;
import com.ybn.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<TicketUser> op = this.userRepository.findByUsername(s);
        if (op.isPresent()) {
            return new CustomUser(op.get());
        }
        throw  new UsernameNotFoundException("Unable to retrieve user with the given username");
    }

    public static class CustomUser extends User {
        public CustomUser(TicketUser u) {
            super(u.getUsername(), u.getPassword(), u.getAuthorities().stream().map(x -> new GrantedAuhtorityWrapper(x)).collect(Collectors.toList()));
        }
    }

    public static class GrantedAuhtorityWrapper implements GrantedAuthority {
        private Authority authority;
        public GrantedAuhtorityWrapper(Authority a) {
            this.authority = a;
        }
        @Override
        public String getAuthority() {
            return authority.getRole();
        }
    }
}
