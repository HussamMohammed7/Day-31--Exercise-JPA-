package com.example.capstonedatabase.Service;


import com.example.capstonedatabase.Model.Merchant;
import com.example.capstonedatabase.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {

        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(Integer id, Merchant merchant) {
        Merchant existingMerchant = merchantRepository.findById(id).orElse(null);
        if (existingMerchant == null) {
            return false;
        }

        existingMerchant.setName(merchant.getName());
        merchantRepository.save(existingMerchant);
        return true;
    }

    public boolean removeMerchant(Integer id) {
        Merchant existingMerchant = merchantRepository.findById(id).orElse(null);
        if (existingMerchant == null) {
            return false;
        }
        merchantRepository.delete(existingMerchant);
        return true;
    }




}