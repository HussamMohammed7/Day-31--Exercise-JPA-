package com.example.capstonedatabase.Repository;

import com.example.capstonedatabase.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant , Integer> {
}
