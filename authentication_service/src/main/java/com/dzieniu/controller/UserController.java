package com.dzieniu.controller;

import com.dzieniu.entity.UserDto;
import com.dzieniu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public void signUp(@RequestBody UserDto userDto){
        userService.insert(userDto);
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void updateOwnProfile(@RequestBody UserDto userDto){
        com.dzieniu.entity.User user = userService.getByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        userService.update(user.getId(), userDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserDto> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserDto getUserById(@PathVariable long id){
        return userService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void insertNewUser(@RequestBody UserDto userDto){
        userService.insert(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void updateExistingUser(@PathVariable long id, @RequestBody UserDto userDto){
        userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteUserById(@PathVariable long id){
        userService.deleteById(id);
    }
}
