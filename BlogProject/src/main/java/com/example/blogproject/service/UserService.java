package com.example.blogproject.service;

import com.example.blogproject.dto.UserDto;
import com.example.blogproject.entity.User;
import com.example.blogproject.mapper.UserMapper;
import com.example.blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(userMapper::toDto).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User userToCreate = userMapper.toEntity(userDto);
        User createdUser = userRepository.save(userToCreate);
        return userMapper.toDto(createdUser);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            userRepository.save(existingUser);
            return userMapper.toDto(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
