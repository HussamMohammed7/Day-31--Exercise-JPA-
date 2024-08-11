package com.example.capstonedatabase.Repository;

import com.example.capstonedatabase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
