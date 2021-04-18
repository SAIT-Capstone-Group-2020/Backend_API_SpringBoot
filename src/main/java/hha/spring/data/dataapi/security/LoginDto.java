package hha.spring.data.dataapi.security;

import com.sun.istack.NotNull;

/**
 * Dummy class to retrieve the request body data.
 */
public class LoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String user_name;

    /**
     * Instantiates a new Login dto.
     *
     * @param email     the email
     * @param password  the password
     * @param user_name the user name
     */
    public LoginDto(String email, String password, String user_name) {
        this.email = email;
        this.password = password;
        this.user_name = user_name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUser_name() {
        return user_name;
    }
}
