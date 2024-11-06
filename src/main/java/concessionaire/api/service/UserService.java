package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.City;
import concessionaire.api.model.Role;
import concessionaire.api.model.User;
import concessionaire.api.repository.CityRepository;
import concessionaire.api.repository.RoleRepository;
import concessionaire.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            return null;

        City city = cityRepository.findById(user.getCity().getId()).orElse(null);
        Role role = roleRepository.findById(user.getRole().getId()).orElse(null);

        user.setCity(city);
        user.setRole(role);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null)
            return null;

        City city = cityRepository.findById(user.getCity().getId()).orElse(null);
        Role role = roleRepository.findById(user.getRole().getId()).orElse(null);

        existingUser.setCity(city == null ? existingUser.getCity() : city);
        existingUser.setRole(role == null ? existingUser.getRole() : role);
        existingUser.setFirstName(user.getFirstName() == null ? existingUser.getFirstName() : user.getFirstName());
        existingUser.setLastName(user.getLastName() == null ? existingUser.getLastName() : user.getLastName());
        existingUser.setEmail(user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        existingUser.setPhone(user.getPhone() == null ? existingUser.getPhone() : user.getPhone());
        existingUser.setAddress(user.getAddress() == null ? existingUser.getAddress() : user.getAddress());
        existingUser.setDob(user.getDob() == null ? existingUser.getDob() : user.getDob());

        return userRepository.save(existingUser);
    }
}
