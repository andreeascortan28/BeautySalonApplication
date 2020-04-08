package org.loose.fis.registration.example.exceptions;

public class UsernameAlreadyExists extends RuntimeException {

    private String username;

    public UsernameAlreadyExists(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
