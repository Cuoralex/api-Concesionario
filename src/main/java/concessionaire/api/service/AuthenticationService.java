package concessionaire.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import concessionaire.api.dto.LoginUserDto;
import concessionaire.api.model.City;
import concessionaire.api.model.Role;
import concessionaire.api.model.User;
import concessionaire.api.repository.CityRepository;
import concessionaire.api.repository.RoleRepository;
import concessionaire.api.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(User input) {

        City city = cityRepository.findById(input.getCity().getId()).orElse(null);
        Role role = roleRepository.findById(input.getRole().getId()).orElse(null);

        input.setCity(city);
        input.setRole(role);
        input.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(input);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return userRepository.findByEmail(input.getEmail());
    }
}