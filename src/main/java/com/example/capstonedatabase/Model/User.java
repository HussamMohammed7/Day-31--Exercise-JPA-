package com.example.capstonedatabase.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain both characters and digits")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Write a proper email")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "Admin|Customer", message = "Role must be either 'Admin' or 'Customer'")
    @Column(columnDefinition = "varchar(20) not null")
    private String role;

    @NotNull(message = "Balance should not be null")
    @Positive(message = "Balance should be positive")
    @Column(columnDefinition = "double not null")
    private double balance;

    @Pattern(regexp = "regular|prime")
    @Column(columnDefinition = "varchar(20) default 'regular'")
    private String subscribed;

    @Column(columnDefinition = "date")
    private LocalDate subscribedDateFinish;

}
