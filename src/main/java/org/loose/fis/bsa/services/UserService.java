package org.loose.fis.bsa.services;
import static org.loose.fis.bsa.services.FileSystemService.getPathToFile;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.LoggedUser;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

// java -jar --module-path F:\javafx-sdk-18.0.1\lib --add-modules javafx.controls,javafx.fxml F:\nitrite-explorer-3.4.3.jar
public class UserService {

    private static ObjectRepository<User> userRepository;

    private static ObjectRepository<Reservation> reservationForUserRepository;

    private static ObjectRepository<Reservation> reservationRepository;

    private static Nitrite database;

    public static void initDatabase() {

        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        //reservationForUserRepository = database.getRepository(Reservation.class);
        reservationRepository = database.getRepository(Reservation.class);

    }



    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static void checkCredentials(String username, String password, String role) throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException {
        int verifPass = 1, verifUser = 1, verifRole = 1;

        for(User user : userRepository.find()) {
            if(Objects.equals(user.getUsername(), username)) {
                verifUser = 0;
                if(Objects.equals(encodePassword(username, password), user.getPassword())) {
                    verifPass = 0;
                    if(Objects.equals(role, user.getRole()))
                        verifRole = 0;
                }
            }
        }

        if(verifUser == 1)
            throw new UsernameDoesNotExistException(username);
        if(verifPass == 1)
            throw new WrongPasswordException();
        if(verifRole == 1)
            throw new WrongRoleException();

    }


    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }



    public static void addReservation(String username, String department, String date, String hour) throws EmptyDateFieldException, EmptyDepartmentFieldException, EmptyHourFieldException, MakingReservationException, NotFreeWindowException {
        checkEmptyFieldForReservation(username, department, date, hour);
        //checkFreeWindow(department, date, hour);
        //checkFreeWindowForUser(username, department, date, hour);
        reservationRepository.insert(new Reservation(username, department, date, hour));
    }

    public static void checkEmptyFieldForReservation(String username, String department, String date, String hour) throws EmptyDateFieldException, EmptyDepartmentFieldException, EmptyHourFieldException {
        if(department == "")
            throw new EmptyDepartmentFieldException();
        else if(date == "")
            throw new EmptyDateFieldException();
        else if(hour == "")
            throw new EmptyHourFieldException();

    }

    public static void checkFreeWindow(String department, String date, String hour) throws MakingReservationException {
        int ok = 0;

        for(Reservation reservation : reservationRepository.find())
        {
            if(department.equals(reservation.getDepartment()))
                if(date.equals(reservation.getDate()) && hour.equals(reservation.getHour()))
                    ok = 1;
        }
        if(ok == 1)
            throw new MakingReservationException();

    }

    public static void checkFreeWindowForUser(String username, String department, String date, String hour) throws NotFreeWindowException {

        int ok = 0;

        for(Reservation reservation : reservationRepository.find()) {
            if(username.equals(reservation.getUsername())) {
                if(date.equals(reservation.getDate()) && hour.equals(reservation.getHour()))
                    ok = 1;
            }
        }
        if(ok == 1)
            throw new NotFreeWindowException();
    }


    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}