package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository USER_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.USER_REPOSITORY = userRepository;
        this.PASSWORD_ENCODER = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());
        }
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return USER_REPOSITORY.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getListOfUsers() {
        return USER_REPOSITORY.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return USER_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        User checkUser = USER_REPOSITORY.findByUsername(user.getUsername());
        if (checkUser == null) {
            user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
            USER_REPOSITORY.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        USER_REPOSITORY.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        USER_REPOSITORY.deleteById(id);
    }
}