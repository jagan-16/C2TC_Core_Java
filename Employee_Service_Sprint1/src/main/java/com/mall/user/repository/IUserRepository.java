package com.mall.user.repository;

import com.mall.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * IUserRepository
 * =========================================================
 * Repository interface for User data access.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findByRole(String role);

    List<User> findByActive(boolean active);
}
