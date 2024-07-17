package com.capestone.learning.service;

import com.capestone.learning.entity.User;
import com.capestone.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public CustomUserDetailsService( UserRepository userRepository
            , @Lazy PasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String addUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            // Handle username already taken scenario
            // Redirect to registration form with error message or handle as appropriate
            return "redirect:/register?error";
        }
        user.setRoles(Set.of("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(user.getPassword());
        userRepository.save(user);
        return "redirect:/login?success";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}
