package org.loose.fis.bsa.services;
import static org.loose.fis.bsa.services.FileSystemService.getPathToFile;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.bsa.exceptions.UsernameDoesNotExistException;
import org.loose.fis.bsa.exceptions.WrongPasswordException;
import org.loose.fis.bsa.exceptions.WrongRoleException;
import org.loose.fis.bsa.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void login (String username, String password, String role) throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException {
        int verifPass = 1, verifUser = 1, verifRole = 1;
        /* devine 0 daca exista */
        for(User user : userRepository.find())
        {
            if(Objects.equals(username, user.getUsername())) verifUser = 0;
            if(Objects.equals(encodePassword(username, password), user.getPassword())) verifPass = 0;
            if(Objects.equals(role, user.getRole())) verifRole = 0;
        }
        if(verifUser == 1)
            throw new UsernameDoesNotExistException(username);
        if(verifPass == 1)
            throw new WrongPasswordException();
        if(verifRole == 1)
            throw new WrongRoleException();

    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
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