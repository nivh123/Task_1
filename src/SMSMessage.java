import java.util.Date;

public class SMSMessage extends Message implements IDigital {
    private String phoneNumber;
    private boolean isDelivered;

    public SMSMessage(String sender, String content, Date date, int messageId, String phoneNumber, boolean isDelivered) throws InvalidPhoneNumberException {
        super(sender, content, date, messageId);
        setPhoneNumber(phoneNumber);
        this.isDelivered = isDelivered;
    }

    public SMSMessage(String sender, String content, int messageId, String phoneNumber, boolean isDelivered) throws InvalidPhoneNumberException {
        this(sender, content, new Date(), messageId, phoneNumber, isDelivered);
    }

    public SMSMessage(String sender, String content, int messageId, String phoneNumber) throws InvalidPhoneNumberException {
        this(sender, content, new Date(), messageId, phoneNumber, true);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches("^\\+?[0-9\\-\\s]{7,15}$")) {
            throw new InvalidPhoneNumberException("Invalid phone number format: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Cellular SMS Gateway";
    }

    @Override
    public String generatePreview() {
        return "[SMS] to: " + phoneNumber + ": " + (content.length() > 15 ? content.substring(0, 15) + "..." : content);
    }

    @Override
    public String toString() {
        return super.toString() + ", SMSMessage [PhoneNumber=" + phoneNumber + ", IsDelivered=" + isDelivered + "]";
    }
}