public class AttachmentException extends Exception {
    public AttachmentException() {
        super("Attachment error occurred.");
    }
    public AttachmentException(String message) {
        super(message);
    }
}