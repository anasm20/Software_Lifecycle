package com.waff.rest.demo.controller;

import com.waff.rest.demo.model.User;
import com.waff.rest.demo.model.UserType;
import com.waff.rest.demo.dto.UserDto;
import com.waff.rest.demo.service.UserService;
import com.waff.rest.demo.controller.UserController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUsersAsAdmin() {
        // Arrange
        User adminUser = new User();
        adminUser.setUserType(UserType.admin);
        
        User regularUser = new User();
        regularUser.setUserType(UserType.user);
        
        List<User> users = Arrays.asList(adminUser, regularUser);

        // Act
        when(userService.getUsers()).thenReturn(users);

        // Assert
        ResponseEntity<List<User>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }
    
    @Test
    void testCreateUserAsAdmin() {
        // Arrange
        UserDto userDto = new UserDto(); // Setzte UserDto Eigenschaften
        User user = new User(); // Setzte User Eigenschaften
        user.setUserType(UserType.admin);
        
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(java.util.Optional.of(user));
        
        // Act
        ResponseEntity<User> response = userController.createUser(userDto);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testCreateUserAsUser() {
        // Arrange
        UserDto userDto = new UserDto(); // Setzte UserDto Eigenschaften
        User user = new User(); // Setzte User Eigenschaften
        user.setUserType(UserType.user);
        
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(java.util.Optional.of(user));
        
        // Act
        ResponseEntity<User> response = userController.createUser(userDto);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
}

