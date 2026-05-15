package com.mall.shopowner.controller;

import com.mall.shopowner.entity.ShopOwner;
import com.mall.shopowner.service.IShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopowners")
@CrossOrigin(origins = "*")
public class ShopOwnerController {

    @Autowired
    private IShopOwnerService shopOwnerService;

    @PostMapping
    public ResponseEntity<?> addShopOwner(@RequestBody ShopOwner shopOwner) {
        try {
            ShopOwner saved = shopOwnerService.addShopOwner(shopOwner);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Validation Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllShopOwners() {
        try {
            List<ShopOwner> shopOwners = shopOwnerService.getAllShopOwners();
            if (shopOwners.isEmpty()) {
                return new ResponseEntity<>("No shop owners found.", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shopOwners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchShopOwner(@PathVariable long id) {
        try {
            ShopOwner shopOwner = shopOwnerService.searchShopOwner(id);
            if (shopOwner == null) {
                return new ResponseEntity<>("ShopOwner with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(shopOwner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShopOwner(@PathVariable int id, @RequestBody ShopOwner shopOwner) {
        try {
            shopOwner.setId(id);
            ShopOwner updated = shopOwnerService.updateShopOwner(shopOwner);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Validation Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShopOwner(@PathVariable long id) {
        try {
            Boolean deleted = shopOwnerService.deleteShopOwner(id);
            if (!deleted) {
                return new ResponseEntity<>("ShopOwner with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("ShopOwner with ID " + id + " deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getShopOwnersByCategory(@PathVariable String category) {
        try {
            List<ShopOwner> shopOwners = shopOwnerService.getShopOwnersByCategory(category);
            if (shopOwners.isEmpty()) {
                return new ResponseEntity<>("No shop owners found for category: " + category, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shopOwners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
