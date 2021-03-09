package hha.spring.data.dataapi.security;

import com.sun.istack.NotNull;

/**
*dummy class to retrieve the request body data.
 */

public class LoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String user_name;

    public LoginDto(String email, String password, String user_name) {
        this.email = email;
        this.password = password;
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_name() {
        return user_name;
    }
}
