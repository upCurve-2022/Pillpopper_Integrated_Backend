package com.Pill.Popper.dao.service;

import java.util.List;

import com.Pill.Popper.dao.entity.UserEntity;
import com.Pill.Popper.exception.ResourceNotFoundException2;

public interface UserService {

	UserEntity save(UserEntity user);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(long userId) throws ResourceNotFoundException2;

    UserEntity updateUser(UserEntity user, long userId) throws ResourceNotFoundException2;

    void deleteUserById(long userId) throws ResourceNotFoundException2;
}