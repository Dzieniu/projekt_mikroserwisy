package com.dzieniu.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    private long id;

    @NotNull
    @Email
    @Size(min = 3,max = 20)
    private String email;

    @NotNull
    @Size(min = 3,max = 60)
    private String username;

    @NotNull
    @Size(min = 3,max = 60)
    private String password;

    @NotNull
    @Size(min = 2,max = 60)
    private String firstName;

    @NotNull
    @Size(min = 2,max = 60)
    private String lastName;

    @NotNull
    private String role;
}
