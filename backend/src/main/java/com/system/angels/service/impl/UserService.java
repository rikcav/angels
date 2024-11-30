package com.system.angels.service.impl;

import com.system.angels.domain.User;
import com.system.angels.dto.create.UserRegisterDTO;
import com.system.angels.dto.response.UserRO;
import com.system.angels.exceptions.UserNotFoundException;
import com.system.angels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserRO> findAll() {
        return repository.findAll().stream().map(this::entityToRO).toList();
    }

    public UserRO findById(Long id) {
        var user = repository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " was not found"));
        return entityToRO(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username " + username + " was not found"));
    }

    public UserRO findByUsernameRO(String username) {
        var user = repository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username " + username + " was not found"));
        return entityToRO(user);
    }

    public UserRO createUser(UserRegisterDTO userRegisterDTO) {
        var existingUser = repository.findByUsername(userRegisterDTO.username());
        if (existingUser.isPresent()) {
            throw new UserNotFoundException("Usuário já cadastrado");
        }

        var user = dtoToEntity(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = repository.save(user);
        return entityToRO(savedUser);
    }

    public UserRO updateUser(Long id, UserRegisterDTO userRegisterDTO) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " was not found"));

        user.setUsername(userRegisterDTO.username());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

        var savedUser = repository.save(user);

        return entityToRO(savedUser);
    }

    public void deleteUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " was not found"));
        userRepository.deleteById(user.getId());
    }

    private User dtoToEntity(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user.setUsername(userRegisterDTO.username());
        user.setPassword(userRegisterDTO.password());
        user.setName(userRegisterDTO.name());

        return user;
    }

    private UserRO entityToRO(User user) {
        return new UserRO(user.getUsername());
    }
}
