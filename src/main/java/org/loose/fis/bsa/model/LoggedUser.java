package org.loose.fis.bsa.model;

public class LoggedUser {

    public static String loggedUser, loggedPassw, loggedRole;

    public static String getLoggedUser() {
        return loggedUser;
    }
    public static void setLoggedUser(String loggedUser) {
        LoggedUser.loggedUser = loggedUser;
    }

    public static String getLoggedPassw() {
        return loggedPassw;
    }
    public static void setLoggedPassw(String loggedPassw) {
        LoggedUser.loggedPassw = loggedPassw;
    }

    public static String getLoggedRole() {
        return loggedRole;
    }
    public static void setLoggedRole(String loggedRole) {
        LoggedUser.loggedRole = loggedRole;
    }

}
