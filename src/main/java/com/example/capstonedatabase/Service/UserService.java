package com.example.capstonedatabase.Service;

import com.example.capstonedatabase.Model.User;
import com.example.capstonedatabase.Model.Messages;
import com.example.capstonedatabase.Repository.ProductRepository;
import com.example.capstonedatabase.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MerchantStockService merchantStockService;
    private final ProductRepository productRepository;

    private final GiftCardService giftCardService;

    private final MerchantService merchantService;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {

        userRepository.save(user);
        return true;
    }

    public boolean updateUser(Integer id, User user) {
        User existingUser = userRepository.getById(id);
        if (existingUser.getUsername() == null) {
            return false;
        }
        user.setId(id);
        userRepository.save(user);
        return true;
    }

    public boolean removeUser(Integer id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }


//    public User login(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;
//    }
//
//    public int resetPassword(String id, String password, String confirmPassword) {
//        User user = getUserById(id);
//        if (user == null) {
//            return -1;
//        }
//        if (!password.equals(confirmPassword)) {
//            return -2;
//        }
//        if (password.length() < 7) {
//            return -4;
//        }
//        user.setPassword(password);
//        userRepository.save(user);
//        return 0;
//    }

    public int buyProduct(Integer userId, Integer productId, Integer merchantId) {
        User currentUser = userRepository.getById(userId);
        if (currentUser.getUsername() == null || isAdmin(userId)) {
            return -1;
        }
        if (merchantStockService.getMerchantStockByProductAndMerchant(productId, merchantId) == null) {
            return -2;
        }
        if (!merchantStockService.isProductInStock(merchantId, productId)) {
            return -3;
        }
        double priceProduct = productRepository.getById(productId).getPrice();
        if ("prime".equals(currentUser.getSubscribed())) {
            priceProduct -= priceProduct * 0.15;
        }
        if (!balanceDeposit(userId, priceProduct)) {
            return -4;
        }
        merchantStockService.reduceStock(merchantId, productId);


        userRepository.save(currentUser);
        return 0;
    }

    public boolean balanceDeposit(Integer userId, double amount) {
        User user = userRepository.getById(userId);
        if (user != null && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            userRepository.save(user);
            return true;
        }
        return false;
    }




    public boolean renewSubscription(Integer userId) {
        User user = userRepository.getById(userId);
        if (user.getUsername() == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        if (user.getSubscribedDateFinish() == null || user.getSubscribedDateFinish().isBefore(currentDate)) {
            user.setSubscribedDateFinish(currentDate.plusMonths(1));
        } else {
            user.setSubscribedDateFinish(user.getSubscribedDateFinish().plusMonths(1));
        }
        user.setSubscribed("prime");
        userRepository.save(user);
        return true;
    }

    public boolean redeemGiftCard(Integer userId, String code) {
        User user = userRepository.getById(userId);
        return user != null && giftCardService.redeemGiftCard(userId, code);
    }

    public boolean isAdmin(Integer userId) {
        User user = userRepository.getById(userId);
        return user != null && "Admin".equals(user.getRole());
    }
}
