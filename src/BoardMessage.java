import java.util.Date;

public class BoardMessage extends Message {
    private PriorityType priority;
    private String category;

    public BoardMessage(String sender , String content , Date date , int messageId , PriorityType priority , String category) {
        super(sender, content, date, messageId); 
        setPriority(priority);
        setCategory(category);
    }

    public BoardMessage(String sender, String content, int messageId, PriorityType priority, String category) {
        this(sender, content, new Date(), messageId, priority, category);
    }

    public BoardMessage(String sender, String content, int messageId, String category) {
        this(sender, content, new Date(), messageId, PriorityType.REGULAR, category);
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority != null ? priority : PriorityType.REGULAR;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        this.category = category;
    }
    
    @Override
    public String generatePreview() {
        return "[Board] " + sender + ": " + (content.length() > 15 ? content.substring(0, 15) + "..." : content); 
    }

    @Override
    public String toString() {
        return super.toString() + ", BoardMessage [Priority=" + priority + ", Category=" + category + "]";
    }
     public boolean isHighPriority() {
        return this.priority == PriorityType.CRITICAL; 
    }
}
