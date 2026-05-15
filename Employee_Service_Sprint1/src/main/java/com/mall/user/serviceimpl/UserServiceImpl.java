package com.mall.user.serviceimpl;

import com.mall.user.entity.User;
import com.mall.user.exception.UserNotFoundException;
import com.mall.user.repository.IUserRepository;
import com.mall.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * UserServiceImpl
 * =========================================================
 * Implementation of IUserService.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
        boolean exists = userRepository.existsById(user.getId());
        if (!exists) {
            throw new UserNotFoundException("User with ID " + user.getId() + " not found. Cannot update.");
        }
        return userRepository.save(user);
    }

    @Override
    public User searchUser(long id) {
        Optional<User> optional = userRepository.findById((int) id);
        return optional.orElse(null);
    }

    @Override
    public Boolean deleteUser(long id) {
        boolean exists = userRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        userRepository.deleteById((int) id);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.orElse(null);
    }

    @Override
    public User loginUser(String username, String password) {
        Optional<User> optional = userRepository.findByUsernameAndPassword(username, password);
        return optional.orElse(null);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}
