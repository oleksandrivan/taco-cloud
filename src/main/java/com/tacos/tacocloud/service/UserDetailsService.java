package com.tacos.tacocloud.service;

import com.tacos.tacocloud.entity.TacoUser;
import com.tacos.tacocloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mono<TacoUser> user = userRepository.findByUsername(username);
        if (user != null) {
            return user.block();
        }
        throw new UsernameNotFoundException(String.format("User %s not found", username));
    }
}
