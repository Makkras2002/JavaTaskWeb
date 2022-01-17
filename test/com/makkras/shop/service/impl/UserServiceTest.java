package com.makkras.shop.service.impl;

import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.CustomUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {
    private static final String EMPTY_ROLE = "-";
    private static Logger logger = LogManager.getLogger();

    @Mock
    private CustomUserService userService;

    private String login;
    private String email;
    private String role;
    private Boolean status;

    @BeforeClass
    public void setUp() {
        login = "Papa";
        email = "Shpak70Ig@mail.ru";
        role = "admin";
        status = false;
        userService = Mockito.mock(UserService.class);
        try {
            Mockito.when(userService.findAllUsers()).thenThrow(ServiceException.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = {ServiceException.class})
    public void findActiveUsersFromDbByParamsExceptionTest() throws ServiceException{
        List<User> allUsers = null;
        allUsers = userService.findAllUsers();
        List<User> requiredUsers = new ArrayList<>();
        int counter = 0;
        while (counter < allUsers.size()){
            if(((allUsers.get(counter).getLogin().contains(login) && login.length()>3) || login.isBlank()) &&
                    ((allUsers.get(counter).getEmail().contains(email) && email.length()>3) || email.isBlank()) &&
                    (allUsers.get(counter).getUserRoleName().toLowerCase().equals(role) || role.equals(EMPTY_ROLE))) {
                if(status == null) {
                    requiredUsers.add(allUsers.get(counter));
                } else if(allUsers.get(counter).isOnline() == status){
                    requiredUsers.add(allUsers.get(counter));
                }
            }
            counter++;
        }
    }

    @AfterClass
    public void after() {
        try {
            Mockito.verify(userService,Mockito.atLeastOnce()).findAllUsers();
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }
}