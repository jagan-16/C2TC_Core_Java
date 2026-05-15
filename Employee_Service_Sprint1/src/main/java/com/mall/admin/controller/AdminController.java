package com.mall.admin.controller;

import com.mall.admin.entity.Admin;
import com.mall.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        try {
            Admin saved = adminService.addAdmin(admin);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Validation Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            if (admins.isEmpty()) {
                return new ResponseEntity<>("No admins found.", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchAdmin(@PathVariable long id) {
        try {
            Admin admin = adminService.searchAdmin(id);
            if (admin == null) {
                return new ResponseEntity<>("Admin with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        try {
            admin.setId(id);
            Admin updated = adminService.updateAdmin(admin);
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
    public ResponseEntity<?> deleteAdmin(@PathVariable long id) {
        try {
            Boolean deleted = adminService.deleteAdmin(id);
            if (!deleted) {
                return new ResponseEntity<>("Admin with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Admin with ID " + id + " deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getAdminsByRole(@PathVariable String role) {
        try {
            List<Admin> admins = adminService.getAdminsByRole(role);
            if (admins.isEmpty()) {
                return new ResponseEntity<>("No admins found for role: " + role, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");
            Admin admin = adminService.loginAdmin(email, password);
            if (admin == null) {
                return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
