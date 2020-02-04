package com.dzieniu.service;

import com.dzieniu.entity.User;
import com.dzieniu.entity.UserDto;
import com.dzieniu.entity.UserMapper;
import com.dzieniu.repository.UserRepository;
import com.dzieniu.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll(){
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto getById(long id){
        return userMapper.toDto(userRepository.findById(id).get());
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void insert(UserDto userDto){
        User user = userMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(long id, UserDto update){
        User user = userRepository.findById(id).get();
        if(update.getUsername() != null) user.setUsername(update.getUsername());
        if(update.getPassword() != null) user.setPassword(update.getPassword());
        if(update.getRole() != null) user.setRole(Role.valueOf(update.getRole()));
        if(update.getFirstName() != null) user.setFirstName(update.getFirstName());
        if(update.getLastName() != null) user.setLastName(update.getLastName());
        if(update.getEmail() != null) user.setEmail(update.getEmail());
        userRepository.save(user);
    }

    public void deleteById(long id){
        if(!userRepository.findById(id).get().getRole().equals(Role.ROLE_ADMIN)){
            userRepository.deleteById(id);
        }
    }
}
