package com.mall.shopowner.serviceimpl;

import com.mall.shopowner.entity.ShopOwner;
import com.mall.shopowner.exception.ShopOwnerNotFoundException;
import com.mall.shopowner.repository.IShopOwnerRepository;
import com.mall.shopowner.service.IShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * ShopOwnerServiceImpl
 * =========================================================
 * Implementation of IShopOwnerService.
 */
@Service
public class ShopOwnerServiceImpl implements IShopOwnerService {

    @Autowired
    private IShopOwnerRepository shopOwnerRepository;

    @Override
    public ShopOwner addShopOwner(ShopOwner shopOwner) {
        if (shopOwner == null) {
            throw new IllegalArgumentException("ShopOwner object cannot be null.");
        }
        if (shopOwner.getName() == null || shopOwner.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("ShopOwner name cannot be null or empty.");
        }
        if (shopOwner.getShopName() == null || shopOwner.getShopName().trim().isEmpty()) {
            throw new IllegalArgumentException("Shop name cannot be null or empty.");
        }
        return shopOwnerRepository.save(shopOwner);
    }

    @Override
    public ShopOwner updateShopOwner(ShopOwner shopOwner) {
        if (shopOwner == null) {
            throw new IllegalArgumentException("ShopOwner object cannot be null.");
        }
        boolean exists = shopOwnerRepository.existsById(shopOwner.getId());
        if (!exists) {
            throw new ShopOwnerNotFoundException("ShopOwner with ID " + shopOwner.getId() + " not found. Cannot update.");
        }
        return shopOwnerRepository.save(shopOwner);
    }

    @Override
    public ShopOwner searchShopOwner(long id) {
        Optional<ShopOwner> optional = shopOwnerRepository.findById((int) id);
        return optional.orElse(null);
    }

    @Override
    public Boolean deleteShopOwner(long id) {
        boolean exists = shopOwnerRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        shopOwnerRepository.deleteById((int) id);
        return true;
    }

    @Override
    public List<ShopOwner> getAllShopOwners() {
        return shopOwnerRepository.findAll();
    }

    @Override
    public List<ShopOwner> getShopOwnersByCategory(String category) {
        return shopOwnerRepository.findByShopCategory(category);
    }
}
