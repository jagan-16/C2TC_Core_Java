package com.mall.admin.repository;

import com.mall.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * IAdminRepository
 * =========================================================
 * Repository interface for Admin data access.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    List<Admin> findByRole(String role);

    Optional<Admin> findByEmailAndPassword(String email, String password);
}
