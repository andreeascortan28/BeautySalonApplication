package org.loose.fis.bsa.services;
import static org.loose.fis.bsa.services.FileSystemService.getPathToFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


// java -jar --module-path F:\javafx-sdk-18.0.1\lib --add-modules javafx.controls,javafx.fxml F:\nitrite-explorer-3.4.3.jar

public class UserService {

    private static ObjectRepository<User> userRepository;

    private static ObjectRepository<Reservation> reservationRepository;

    private static ObjectRepository<DepartmentFacility> departmentFacilityRepository;

    private static Nitrite database;

    public static void initDatabase() {

        database = Nitrite.builder()
                .filePath(getPathToFile("\\registration-example.db").toFile())
                .openOrCreate("test", "test");
        userRepository = database.getRepository(User.class);
        reservationRepository = database.getRepository(Reservation.class);
        departmentFacilityRepository = database.getRepository(DepartmentFacility.class);
        addDepartments();

    }


    public static ObjectRepository<Reservation> getReservationRepository() {
        return reservationRepository;
    }

    public static ObjectRepository<DepartmentFacility> getDepartmentFacilityRepository()
    { return departmentFacilityRepository; }


    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static void addReservation(String username, String departmentfacility, String date, String hour, int price) throws EmptyDateFieldException, EmptyDepartmentFieldException, EmptyHourFieldException, MakingReservationException, NotFreeWindowException, WrongDateException {

        checkFreeWindowForUser(username, date, hour);
        checkFreeWindow(departmentfacility, date, hour);
        checkDate(date);
        reservationRepository.insert(new Reservation(username, departmentfacility, date, hour, price));

    }

    public static  ObservableList<Edit> getAllFacilitiesByDept(String dept){
        ObservableList<Edit> ol = FXCollections.observableArrayList();
        for(DepartmentFacility dp : departmentFacilityRepository.find()){
            String[] parts = dp.getDepartmentfacility().split(" - ");
            //System.out.println(parts[0]+" "+dept);
            if(parts[0].equals(dept))
                ol.add(new Edit(parts[1],dp.getPrice()));
        }
        return ol;
    }
    public static void updateDeptPrice(String name, int newPrice){
        departmentFacilityRepository.update(ObjectFilters.eq("departmentfacility",name),
                new DepartmentFacility(name,newPrice));
    }

    public static void updateReservations(String user, String depfac, String date, String hour, int price){
        reservationRepository.update(ObjectFilters.eq("username",user), new Reservation(user,depfac,date,hour,price));
    }

    public static void changePrice(String facil, Integer price) {

        for(DepartmentFacility departmentfacility : departmentFacilityRepository.find()) {
            String[] parts = ((String) departmentfacility.getDepartmentfacility()).split(" - ");
            String facility = parts[1]; //ce i in baza de date

            if(Objects.equals(facility, facil)) {
                departmentfacility.setPrice(price);
                departmentFacilityRepository.update(departmentfacility);
            }
        }

    }

    public static void addDepartments() {
        if(departmentFacilityRepository.find().size()!=0)
            return;
        departmentFacilityRepository.remove(ObjectFilters.ALL);

        //preturile puse fix la inceput

        departmentFacilityRepository.insert(new DepartmentFacility("Hair salon - Hair cutting", 80));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair salon - Colouring", 220));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair salon - Styling", 150));

        departmentFacilityRepository.insert(new DepartmentFacility("Make-up - Everyday make-up", 50));
        departmentFacilityRepository.insert(new DepartmentFacility("Make-up - Wedding make-up", 100));

        departmentFacilityRepository.insert(new DepartmentFacility("Hair removal - Face", 30));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair removal - Legs", 100));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair removal - Back and chest", 50));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair removal - Underarms", 20));
        departmentFacilityRepository.insert(new DepartmentFacility("Hair removal - Arms", 50));

        departmentFacilityRepository.insert(new DepartmentFacility("Nails - Manicure", 80));
        departmentFacilityRepository.insert(new DepartmentFacility("Nails - Pedicure", 50));
        departmentFacilityRepository.insert(new DepartmentFacility("Nails - Mani-Pedi", 100));

        departmentFacilityRepository.insert(new DepartmentFacility("Facial treatments - Classic facial", 100));
        departmentFacilityRepository.insert(new DepartmentFacility("Facial treatments - Acne reduction facial", 150));
        departmentFacilityRepository.insert(new DepartmentFacility("Facial treatments - LED light therapy", 300));
        departmentFacilityRepository.insert(new DepartmentFacility("Facial treatments - Acupunture facial", 250));


        departmentFacilityRepository.insert(new DepartmentFacility("Massage - Classic facial", 100));
        departmentFacilityRepository.insert(new DepartmentFacility("Massage - Deep tissue massage", 150));
        departmentFacilityRepository.insert(new DepartmentFacility("Massage - Hot stone massage", 200));
        departmentFacilityRepository.insert(new DepartmentFacility("Massage - Sports massage", 150));
        departmentFacilityRepository.insert(new DepartmentFacility("Massage - Thai massage", 300));


    }

    public static void deleteReservation(Reservation reservation){
        reservationRepository.remove(reservation);
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

    public static void checkIfAllFieldsAreCompleted(String password, String username, String firstName, String lastName, String phone, String email) throws AllFieldsAreMandatory{
        if(password == "" || username == "" || firstName == "" || lastName == "" || phone == "" || email == "" )
            throw new AllFieldsAreMandatory();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }



    public static void checkEmptyFieldForReservation(ChoiceBox departmentfacility, String date, ChoiceBox hour) throws EmptyDateFieldException, EmptyDepartmentFieldException, EmptyHourFieldException {
        if(departmentfacility.getSelectionModel().isEmpty())
            throw new EmptyDepartmentFieldException();
        else if(date == "")
            throw new EmptyDateFieldException();
        else if(hour.getSelectionModel().isEmpty())
            throw new EmptyHourFieldException();

    }

    public static void checkEmptyFieldForLogin(String username, String password, ChoiceBox role) throws AllFieldsAreMandatory {
        if(username == "" || password == "" || role.getSelectionModel().isEmpty())
            throw new AllFieldsAreMandatory();
    }

    private static void checkFreeWindow(String departmentfacility, String date, String hour) throws MakingReservationException {
        int ok = 0;
        //System.out.println("Am intrat in check free window");
        for(Reservation reservation : reservationRepository.find())
        {
            if(Objects.equals(reservation.getDepartmentfacility(), departmentfacility))
                if(Objects.equals(date, reservation.getDate()) && Objects.equals(hour, reservation.getHour()))
                    ok = 1;
        }
        if(ok == 1)
            throw new MakingReservationException();

    }

    public static void checkFreeWindowForUser(String username, String date, String hour) throws NotFreeWindowException {

        int ok = 0;
        System.out.println("am intrat in check free window user");
        for(Reservation reservation : reservationRepository.find()) {
            if(username.equals(reservation.getUsername())) {
                if(date.equals(reservation.getDate()) && hour.equals(reservation.getHour()))
                    ok = 1;
            }
        }
        if(ok == 1)
            throw new NotFreeWindowException();
    }

    public static void checkDate(String date) throws WrongDateException {

        int ok = 1;
        if(date != "") {
            if(date.length() != 10)
                throw new WrongDateException();
            if (date.charAt(2) != '/' || date.charAt(5) != '/')
                throw new WrongDateException();
            String parts[] = date.split("/");
            if(Integer.parseInt(parts[0]) < 1 || Integer.parseInt(parts[0]) > 31) ok = 0;
            if(Integer.parseInt(parts[1]) < 1 || Integer.parseInt(parts[0]) > 12) ok = 0;
            if(Integer.parseInt(parts[2]) < 2022) ok = 0;
        }



        if(ok == 0)
            throw new WrongDateException();

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