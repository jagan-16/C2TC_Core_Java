package com.mall.shopowner.service;

import com.mall.shopowner.entity.ShopOwner;

import java.util.List;

/**
 * =========================================================
 * IShopOwnerService Interface
 * =========================================================
 * Service layer contract for ShopOwner business logic.
 */
public interface IShopOwnerService {

    ShopOwner addShopOwner(ShopOwner shopOwner);

    ShopOwner updateShopOwner(ShopOwner shopOwner);

    ShopOwner searchShopOwner(long id);

    Boolean deleteShopOwner(long id);

    List<ShopOwner> getAllShopOwners();

    List<ShopOwner> getShopOwnersByCategory(String category);
}
