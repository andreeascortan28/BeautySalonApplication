package org.loose.fis.bsa.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".Beauty-Salon-Application";
//    private static final String USER_FOLDER = System.getProperty("user.home");
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    /*public static Path getPathToFile(String... path) {
       return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }*/
    public static Path getPathToFile(String path) {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER,path);
    }

    public static void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

}
