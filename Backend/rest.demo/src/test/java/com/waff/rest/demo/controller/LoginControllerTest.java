package com.waff.rest.demo.controller;

import com.waff.rest.demo.dto.LoginRequestDto;
import com.waff.rest.demo.model.DaoUserDetails;
import com.waff.rest.demo.model.User;
import com.waff.rest.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationProvider authenticationProvider;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private HttpServletRequest httpRequest;
    @Mock
    private HttpSession httpSession;
    @Mock
    private Authentication authentication;
    @Mock
    private DaoUserDetails daoUserDetails;

    private LoginRegisterController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new LoginRegisterController(userService, authenticationProvider, modelMapper);
    }

    @Test
    void loginTest() throws Exception {
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("user");
        requestDto.setPassword("password");

        when(authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(daoUserDetails);
        when(httpRequest.getSession(true)).thenReturn(httpSession);
        when(daoUserDetails.getUser()).thenReturn(new User());

        ResponseEntity<User> response = controller.login(requestDto, httpRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
