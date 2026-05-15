package com.mall.user.service;

import com.mall.user.entity.User;

import java.util.List;

/**
 * =========================================================
 * IUserService Interface
 * =========================================================
 * Service layer contract for User business logic.
 */
public interface IUserService {

    User addUser(User user);

    User updateUser(User user);

    User searchUser(long id);

    Boolean deleteUser(long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User loginUser(String username, String password);

    List<User> getUsersByRole(String role);
}
