package com.waff.rest.demo.controller;

import com.waff.rest.demo.model.User;
import com.waff.rest.demo.model.UserType;
import com.waff.rest.demo.dto.UserDto;
import com.waff.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.mock;
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
        User adminUser = new User();
        adminUser.setUserType(UserType.admin);
        User regularUser = new User();
        regularUser.setUserType(UserType.user);
        List<User> users = Arrays.asList(adminUser, regularUser);

        when(userService.getUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testCreateUserAsAdmin() {
        UserDto userDto = new UserDto();
        User user = new User();
        user.setUserType(UserType.admin);
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(java.util.Optional.of(user));

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        ResponseEntity<User> response = userController.createUser(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testCreateUserAsUser() {
        UserDto userDto = new UserDto();
        User user = new User();
        user.setUserType(UserType.user);
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(java.util.Optional.of(user));

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        ResponseEntity<User> response = userController.createUser(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
}
