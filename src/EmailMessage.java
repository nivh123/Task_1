import java.util.Date;
import java.util.ArrayList;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String sender, String content, Date date, int messageId, String subject, ArrayList<File> attachments) {
        super(sender, content, date, messageId);
        setSubject(subject);
        this.attachments = attachments != null ? attachments : new ArrayList<>();
    }

    public EmailMessage(String sender, String content, int messageId, String subject, ArrayList<File> attachments) {
        this(sender, content, new Date(), messageId, subject, attachments);
    }

    public EmailMessage(String sender, String content, int messageId, String subject) {
        this(sender, content, new Date(), messageId, subject, new ArrayList<>());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty.");
        }
        this.subject = subject;
    }

    public void addAttachment(File file) {
        if (file != null) {
            attachments.add(file);
        }
    }

    public void removeAttachment(File file) throws AttachmentException {
        if (file == null) {
            throw new AttachmentException("Cannot remove null file.");
        }
        File targetToRemove = null;
        for (File f : attachments) {
            if (f.getFileName().equals(file.getFileName()) && f.getFileType().equals(file.getFileType())) {
                targetToRemove = f;
                break;
            }
        }
        if (targetToRemove == null) {
            throw new AttachmentException("Attachment not found in email message.");
        }
        attachments.remove(targetToRemove);
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject + " | From: " + sender;
    }

    @Override
    public String toString() {
        return super.toString() + ", EmailMessage [Subject=" + subject + ", Attachments Count=" + attachments.size() + "]";
    }
}