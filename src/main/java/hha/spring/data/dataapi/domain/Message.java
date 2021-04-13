package hha.spring.data.dataapi.domain;

/**
 * This class is to create the object of return data with the okay http status.
 * Controller can specify the return message
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
public class Message {

    private String status; //error | okay
    private String message;

    public Message() {
    }

    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
