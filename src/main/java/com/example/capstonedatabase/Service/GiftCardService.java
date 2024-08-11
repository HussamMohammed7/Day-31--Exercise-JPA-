package com.example.capstonedatabase.Service;

import com.example.capstonedatabase.Model.GiftCard;
import com.example.capstonedatabase.Model.User;
import com.example.capstonedatabase.Repository.GiftCardRepository;
import com.example.capstonedatabase.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GiftCardService {

    private final GiftCardRepository giftCardRepository;
    private final UserRepository userRepository;

    public boolean createGiftCard(double amount) {
        if (amount <= 0) {
            return false;
        }
        String code = generateGiftCardCode();
        GiftCard giftCard = new GiftCard();
        giftCard.setCode(code);
        giftCard.setAmount(amount);
        giftCard.setRedeemed(false);
        giftCardRepository.save(giftCard);
        return true;
    }

    private String generateGiftCardCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000) + 1;
        return "Hussam" + randomNumber;
    }

    public GiftCard validateGiftCard(String code) {
        List<GiftCard> allGiftCards = giftCardRepository.findAll();
        for (GiftCard giftCard : allGiftCards) {
            if (giftCard.getCode().equals(code) && !giftCard.isRedeemed()) {
                return giftCard;
            }
        }
        return null;
    }

    public boolean redeemGiftCard(Integer userId, String code) {
        GiftCard giftCard = validateGiftCard(code);
        if (giftCard == null) {
            return false;
        }

        User user = userRepository.getById(userId);
        if (user.getUsername() == null) {
            return false;
        }

        user.setBalance(user.getBalance() + giftCard.getAmount());
        userRepository.save(user);
        giftCard.setRedeemed(true);
        giftCardRepository.save(giftCard);
        return true;
    }

    public List<GiftCard> getAllGiftCards() {
        return giftCardRepository.findAll();
    }
}
