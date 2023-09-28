package com.waff.rest.demo.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.waff.rest.demo.model.UserType;
import com.waff.rest.demo.model.User;
import com.waff.rest.demo.repository.UserRepository;



@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    
    private UserService userService;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private UserRepository userRepository;
    
    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }
    
    // Unit Test
    @Test
    public void whenGetAllUsers_thenGetAListOfUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
    
        when(userRepository.findAll()).thenReturn(userList);
    
        List<User> newUserList = userService.getUsers();
    
        assertEquals(userList, newUserList);
    }
    
}    




