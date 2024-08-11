package com.example.capstonedatabase.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Code cannot be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String code;

    @Min(value = 0, message = "Amount must be positive")
    @Column(columnDefinition = "double not null")
    private double amount;

    @AssertFalse(message = "Gift card should not be redeemed")
    @Column(columnDefinition = "boolean not null")
    private boolean redeemed;
}
