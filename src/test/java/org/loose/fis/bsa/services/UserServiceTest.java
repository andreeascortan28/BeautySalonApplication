package org.loose.fis.bsa.services;

import javafx.scene.control.ChoiceBox;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.model.User;

import java.util.Date;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String DEPARTMENTFACILITY = "Hair salon - Styling";
    public static final String DATE = "02/04/2022";
    public static final String HOUR = "14:00";
    public static final int PRICE = 150;



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
        FileUtils.cleanDirectory(FileSystemService.getApplicationFolder().toFile());
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


    @Test
    @DisplayName("Reservation is added successfully")
    void testAddReservation() throws EmptyDepartmentFieldException, EmptyHourFieldException, EmptyDateFieldException, MakingReservationException, NotFreeWindowException, WrongDateException{
        UserService.addReservation(USERNAME, DEPARTMENTFACILITY, DATE, HOUR, PRICE);
        assertThat(UserService.reservationList()).isNotEmpty();
        assertThat(UserService.reservationList()).size().isEqualTo(1);
        Reservation reservation = UserService.reservationList().get(0);
        assertThat(reservation).isNotNull();
        assertThat(reservation.getUsername()).isEqualTo(USERNAME);
        assertThat(reservation.getDepartmentfacility()).isEqualTo(DEPARTMENTFACILITY);
        assertThat(reservation.getDate()).isEqualTo(DATE);
        assertThat(reservation.getHour()).isEqualTo(HOUR);

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



    @Test
    @DisplayName("There is already an reservation at this hour")
    void testDepartmentNotEmpty(){
        assertThrows(MakingReservationException.class, () -> {
            UserService.checkFreeWindow(DEPARTMENTFACILITY, DATE, HOUR);
        });
    }

    @Test
    @DisplayName("There is already a reservation made at this hour for this user")
    void testUserScheduleNotEmpty(){
        assertThrows(NotFreeWindowException.class, () -> {
            UserService.checkFreeWindow(USERNAME, DATE, HOUR);
        });
    }

    @Test
    @DisplayName("It was entered a wrong form of the date")
    void testReservationWrongDate() {
        assertThrows(WrongDateException.class, () -> {
            UserService.checkDate(DATE);
        });
    }

}