package com.metrix.usermicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import com.metrix.usermicroservice.model.User;
import com.metrix.usermicroservice.model.User.userRole;
import com.metrix.usermicroservice.service.IUserService;

@SpringBootTest(classes = UserMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class UserServiceTest {

    private static User user;
    @Autowired
    private IUserService userService;

    @BeforeAll
    public static void userSetup() {
        user = new User("dummy@gmail.com","DummyFake", "Dummy", "dummy@gmail.com", "https://www.google.com", LocalDateTime.now(),
                "System", LocalDateTime.now(), "System",false);
        user.setRole(userRole.CLIENT);
    }

    @Test
    public void addUser() {
        userService.addUserData(user);
    }

    @Test
    public void avoidDuplicate() {
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            userService.addUserData(user);
        });
    }

    @Test
    public void getClientProfileDetails() {
        user = userService.findByUserEmail("dummy@gmail.com");
        assertEquals("Dummy", user.getName());
        assertNotNull(user.getName());
        assertEquals("dummy@gmail.com", user.getEmail());
        assertNotNull(user.getEmail());
        assertEquals("https://www.google.com", user.getAvatarURL());
        assertNotNull(user.getAvatarURL());
        assertEquals(userRole.CLIENT, user.getRole());
        assertNotNull(user.getRole());

    }

}
