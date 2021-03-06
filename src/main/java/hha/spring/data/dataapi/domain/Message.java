package hha.spring.data.dataapi.domain;

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
