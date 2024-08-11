package com.example.capstonedatabase.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4 , message = "have to be more than 3 length long")
    @Column(columnDefinition = "varchar(120) not null unique")
    private String name;

    @NotNull(message = "price cannot be empty" )
    @Positive(message = "number must be positive ")
    @Column(columnDefinition = "double not null ")
    private double price;

    @NotNull(message = "categoryID cannot be empty" )
    @Column(columnDefinition = "int not null ")
    private Integer categoryID;

}
