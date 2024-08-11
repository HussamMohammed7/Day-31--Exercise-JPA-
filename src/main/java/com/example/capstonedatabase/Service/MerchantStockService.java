package com.example.capstonedatabase.Service;

import com.example.capstonedatabase.Model.MerchantStock;
import com.example.capstonedatabase.Repository.MerchantStockRepository;
import com.example.capstonedatabase.Repository.MerchantRepository;
import com.example.capstonedatabase.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public List<MerchantStock> getMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public int addMerchantStock(MerchantStock merchantStock) {
        boolean productExists = productRepository.findById(merchantStock.getProductId()).isPresent();
        boolean merchantExists = merchantRepository.findById(merchantStock.getMerchantId()).isPresent();

        if (!productExists) {
            return -1;
        }
        if (!merchantExists) {
            return -2;
        }

        merchantStockRepository.save(merchantStock);
        return 1;
    }


    public boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        MerchantStock existingStock = merchantStockRepository.findById(id).orElse(null);
        if (existingStock == null) {
            return false;
        }

        existingStock.setProductId(merchantStock.getProductId());
        existingStock.setMerchantId(merchantStock.getMerchantId());
        existingStock.setStock(merchantStock.getStock());
        merchantStockRepository.save(existingStock);
        return true;
    }

    public boolean removeMerchantStock(Integer id) {
        if (!merchantStockRepository.existsById(id)) {
            return false;
        }

        merchantStockRepository.deleteById(id);
        return true;
    }

    public boolean addMoreStocks(Integer merchantId, Integer productId, int quantity) {
        MerchantStock existingStock = findByMerchantIdAndProductId(merchantId, productId);
        if (existingStock == null) {
            return false;
        }

        existingStock.setStock(existingStock.getStock() + quantity);
        merchantStockRepository.save(existingStock);
        return true;
    }

    public MerchantStock getMerchantStockByProductAndMerchant(Integer productId, Integer merchantId) {
        return findByMerchantIdAndProductId(merchantId, productId);
    }

    public boolean isProductInStock(Integer merchantId, Integer productId) {
        MerchantStock stock = findByMerchantIdAndProductId(merchantId, productId);
        return stock != null && stock.getStock() > 0;
    }

    public boolean reduceStock(Integer merchantId, Integer productId) {
        MerchantStock stock = findByMerchantIdAndProductId(merchantId, productId);
        if (stock == null || stock.getStock() <= 0) {
            return false;
        }

        stock.setStock(stock.getStock() - 1);
        merchantStockRepository.save(stock);
        return true;
    }

    private MerchantStock findByMerchantIdAndProductId(Integer merchantId, Integer productId) {
        for (MerchantStock stock : getMerchantStocks()) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                return stock;
            }
        }
        return null;
    }
}
