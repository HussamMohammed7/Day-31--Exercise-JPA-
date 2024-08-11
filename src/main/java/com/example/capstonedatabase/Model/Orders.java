//package com.example.capstonedatabase.Model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Orders {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotEmpty(message = "Order ID cannot be empty")
//    @Column(name = "order_id", columnDefinition = "varchar(50) not null unique")
//    private String order_id;
//
//    @NotNull(message = "Products cannot be null")
//    @ElementCollection
//    @Column(name = "products", columnDefinition = "text not null")
//    private String products; // Serialized product data, e.g., JSON
//
//    @NotNull(message = "Quantity cannot be null")
//    @Column(columnDefinition = "int not null")
//    private Integer quantity;
//
//    @NotEmpty(message = "Status cannot be empty")
//    @Pattern(regexp = "under process|shipped|finished|canceled",
//            message = "Status must be one of 'under process', 'shipped', 'finished', 'canceled'")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String status;
//
//    @NotNull(message = "Total cannot be null")
//    @Column(columnDefinition = "double not null")
//    private Double total;
//
//    @NotNull(message = "Purchased date and time cannot be null")
//    @Column(columnDefinition = "timestamp not null")
//    private LocalDateTime purchasedAt;
//}
