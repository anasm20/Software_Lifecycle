package com.waff.rest.demo.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.waff.rest.demo.dto.RegisterRequestDto;
import com.waff.rest.demo.model.User;
import com.waff.rest.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class RegisterControllerTest {

    private final UserService userService = mock(UserService.class);
    private final ModelMapper modelMapper = mock(ModelMapper.class);
    private final LoginRegisterController controller = new LoginRegisterController(userService, null, modelMapper);
    
    @Test
    void registerTest() {
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setUsername("user");
        requestDto.setPassword("password");

        User userMock = mock(User.class);
        when(modelMapper.map(requestDto, User.class)).thenReturn(userMock);
        when(userService.createUser(any(User.class))).thenReturn(Optional.of(userMock));

        ResponseEntity<User> response = controller.register(requestDto, null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
