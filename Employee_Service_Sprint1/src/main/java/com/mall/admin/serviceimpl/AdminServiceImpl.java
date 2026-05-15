package com.mall.admin.serviceimpl;

import com.mall.admin.entity.Admin;
import com.mall.admin.exception.AdminNotFoundException;
import com.mall.admin.repository.IAdminRepository;
import com.mall.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * AdminServiceImpl
 * =========================================================
 * Implementation of IAdminService.
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin object cannot be null.");
        }
        if (admin.getName() == null || admin.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Admin name cannot be null or empty.");
        }
        if (admin.getEmail() == null || admin.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Admin email cannot be null or empty.");
        }
        if (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Admin password cannot be null or empty.");
        }
        if (admin.getRole() == null || admin.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Admin role cannot be null or empty.");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin object cannot be null.");
        }
        boolean exists = adminRepository.existsById(admin.getId());
        if (!exists) {
            throw new AdminNotFoundException("Admin with ID " + admin.getId() + " not found. Cannot update.");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin searchAdmin(long id) {
        Optional<Admin> optional = adminRepository.findById((int) id);
        return optional.orElse(null);
    }

    @Override
    public Boolean deleteAdmin(long id) {
        boolean exists = adminRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        adminRepository.deleteById((int) id);
        return true;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> getAdminsByRole(String role) {
        return adminRepository.findByRole(role);
    }

    @Override
    public Admin loginAdmin(String email, String password) {
        Optional<Admin> optional = adminRepository.findByEmailAndPassword(email, password);
        return optional.orElse(null);
    }
}
