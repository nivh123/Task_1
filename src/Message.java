import java.util.Date;
import java.util.ArrayList;

public abstract class Message {
    protected String sender;
    protected String content;
    protected Date date;
    protected int messageId;

    public Message(String sender, String content, Date date, int messageId) {
        setSender(sender);
        setContent(content);
        setSendDate(date);
        setMessageId(messageId);
    }

    public Message(String sender, String content, int messageId) {
        this(sender, content, new Date(), messageId);
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setSender(String sender) {
        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be empty.");
        }
        this.sender = sender;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.content = content;
    }

    public void setSendDate(Date date) {
        this.date = date != null ? date : new Date();
    }

    public void setMessageId(int messageId) {
        if (messageId <= 0) {
            throw new IllegalArgumentException("Message ID must be positive.");
        }
        this.messageId = messageId;
    }

    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty() || content == null) {
            return false;
        }
        for (String word : words) {
            if (content.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    
    
    public boolean containsText(String text) {
        if (text == null || content == null) {
            return false;
        }
        return content.toLowerCase().contains(text.toLowerCase());
    }

    public abstract String generatePreview();

    @Override
    public String toString() {
        return "Message [ID=" + messageId + ", Sender=" + sender + ", Content=" + content + ", Date=" + date + "]";
    }
}