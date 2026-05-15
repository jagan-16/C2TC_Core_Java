package com.mall.admin.service;

import com.mall.admin.entity.Admin;

import java.util.List;

/**
 * =========================================================
 * IAdminService Interface
 * =========================================================
 * Service layer contract for Admin business logic.
 */
public interface IAdminService {

    Admin addAdmin(Admin admin);

    Admin updateAdmin(Admin admin);

    Admin searchAdmin(long id);

    Boolean deleteAdmin(long id);

    List<Admin> getAllAdmins();

    List<Admin> getAdminsByRole(String role);

    Admin loginAdmin(String email, String password);
}
