package com.example.capstonedatabase.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "productId cannot be empty" )
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @NotNull(message = "merchantId cannot be empty" )
    @Column(columnDefinition = "int not null")
    private Integer merchantId;

    @NotNull(message = "price cannot be empty")
    @Positive(message = "number must be positive")
    @Min(value = 10, message = "Minimum number is 10")
    @Column(columnDefinition = "int not null ")
    private int stock;

}
