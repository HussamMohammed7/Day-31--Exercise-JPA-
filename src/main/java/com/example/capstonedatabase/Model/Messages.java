package com.example.capstonedatabase.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255) not null")
    private String message;

    @Column(columnDefinition = "int not null")
    private Integer sender;

    @Column(columnDefinition = "int not null")
    private Integer id_merchant;

    @Column( columnDefinition = "int not null")
    private Integer id_order;

    @Column( columnDefinition = "int not null")
    private Integer id_user;
}