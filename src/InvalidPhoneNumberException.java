public class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException() {
        super("Invalid phone number provided.");
    }
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}