package com.mall.shopowner.repository;

import com.mall.shopowner.entity.ShopOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * IShopOwnerRepository
 * =========================================================
 * Repository interface for ShopOwner data access.
 * Extends JpaRepository which provides built-in CRUD methods.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface IShopOwnerRepository extends JpaRepository<ShopOwner, Integer> {

    Optional<ShopOwner> findByEmail(String email);

    List<ShopOwner> findByShopCategory(String shopCategory);

    List<ShopOwner> findByNameContainingIgnoreCase(String name);
}
