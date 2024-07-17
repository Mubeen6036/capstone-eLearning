package com.capestone.learning.service;
import com.capestone.learning.entity.User;
import com.capestone.learning.jwt.JwtTokenUtil;
import com.capestone.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String login(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            User userMatch = userOptional.get();
            if( passwordEncoder.matches(user.getPassword(), userMatch.getPassword())){
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                ));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwtToken = jwtTokenUtil.generateToken(authentication);
                return jwtToken;
            }
        }
        return null;
    }
}
