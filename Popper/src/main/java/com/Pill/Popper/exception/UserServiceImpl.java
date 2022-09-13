package com.Pill.Popper.exception;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Pill.Popper.dao.entity.UserEntity;
import com.Pill.Popper.dao.repository.UserEntityRepository;
import com.Pill.Popper.dao.service.UserService;

import lombok.NonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityRepository userRepository;
    private ResponseEntity<UserEntity> user;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserEntityRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password.trim()));
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(long userId) throws ResourceNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new ResourceNotFoundException("User", "id", userId);
        }
    }

    @Override
    public UserEntity updateUser(UserEntity user, long userId) throws ResourceNotFoundException {
        Optional<UserEntity>usr = userRepository.findById(userId);
        if(usr.isPresent()){
        	UserEntity user1=usr.get();
            user1.setFirstname(user.getFirstname());
            user1.setLastname(user.getLastname());
            user1.setGender(user.getGender());
            user1.setDob(user.getDob());
            user1.setAge(user.getAge());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setMobile_no(user.getMobile_no());
            user1.setAddress(user.getAddress());
            userRepository.save(user1);
            return user1;
        }
        else{
            throw new ResourceNotFoundException("User", "id", userId);
        }
    }

    @Override
    public void deleteUserById(long userId) throws ResourceNotFoundException {
        Optional<UserEntity> user= userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.deleteById(userId);
        }
        else{
            throw new ResourceNotFoundException("User", "id", userId);
        }

    }
    
    
    
}