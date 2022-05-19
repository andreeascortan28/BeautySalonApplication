package org.loose.fis.bsa.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.User;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
/*

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-BeautySalonApplication";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        System.out.println("After each");
    }

    @Test
    @DisplayName("Customer is added successfully")
    void testAddCustomer() throws UsernameAlreadyExistsException, AllFieldsAreMandatory, WrongRoleException, WrongPasswordException {
        UserService.addUser(USERNAME, PASSWORD, "Customer");
        assertThat(UserService.usersList()).isNotEmpty();
        assertThat(UserService.usersList()).size().isEqualTo(1);
        User user = UserService.usersList().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(user.getRole()).isEqualTo("Customer");
    }

    @Test
    @DisplayName("Employee is added successfully")
    void testAddEmployee() throws UsernameAlreadyExistsException, AllFieldsAreMandatory, WrongRoleException, WrongPasswordException {
        UserService.addUser(USERNAME, PASSWORD, "Employee");
        assertThat(UserService.usersList()).isNotEmpty();
        assertThat(UserService.usersList()).size().isEqualTo(1);
        User user = UserService.usersList().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(user.getRole()).isEqualTo("Employee");
    }


    //Exceptions

    @Test
    @DisplayName("Username does not exist")
    void testUsernameDoesNotExist() {
        assertThrows(UsernameDoesNotExistException.class, () -> {
            UserService.checkCredentials("", PASSWORD, ROLE);
        });
        assertThrows(UsernameDoesNotExistException.class, () -> {
            UserService.checkCredentials(null, PASSWORD, ROLE);
        });
    }

    @Test
    @DisplayName("The username field should not be empty")
    void testFieldsNotEmpty() {
        assertThrows(AllFieldsAreMandatory.class, () -> {
            UserService.checkCredentials(USERNAME, "", ROLE);
        });
        assertThrows(AllFieldsAreMandatory.class, () -> {
            UserService.checkCredentials(USERNAME, PASSWORD, "");
        });
        assertThrows(AllFieldsAreMandatory.class, () -> {
            UserService.checkCredentials(USERNAME, "", ROLE);
        });
    }

    @Test
    @DisplayName("The login failed because the entered password is not correct")
    void testLoginWrongPassword() {
        assertDoesNotThrow(()-> {
            UserService.addUser(USERNAME, PASSWORD, ROLE);
        });

        assertThrows(WrongPasswordException.class, ()-> {
            UserService.checkCredentials(USERNAME, PASSWORD, ROLE);
        });
    }

    @Test
    @DisplayName("The login failed because the chosen role is not correct")
    void testLoginWrongRole() {
        assertDoesNotThrow(()-> {
            UserService.addUser(USERNAME, PASSWORD, ROLE);
        });

        assertThrows(WrongRoleException.class, ()-> {
            UserService.checkCredentials(USERNAME, PASSWORD, ROLE);
        });
    }


*/
}