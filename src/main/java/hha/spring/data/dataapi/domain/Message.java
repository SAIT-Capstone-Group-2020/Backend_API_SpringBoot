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

    /**
     * Instantiates a new Message.
     */
    public Message() {
    }

    /**
     * Instantiates a new Message.
     *
     * @param status  the status
     * @param message the message
     */
    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
