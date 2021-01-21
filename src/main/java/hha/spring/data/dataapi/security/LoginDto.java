package hha.spring.data.dataapi.security;

import com.sun.istack.NotNull;

/**
*dummy class to retrieve the request body data.
 */

public class LoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;


    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
