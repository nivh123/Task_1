import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>(); 

        try {
            messages.add(new BoardMessage("Mor", "Lets go", 1, PriorityType.URGENT, "Administrayion"));
            messages.add(new BoardMessage("Omer", "How are you", 2, PriorityType.REGULAR, "IT"));

            ArrayList<File> files1 = new ArrayList<>();
            files1.add(new File("report", "pdf"));
            messages.add(new EmailMessage("Noam", "I am fine thank you, how are you?", 10, "Project Submission", files1));
            messages.add(new EmailMessage("Yossi", "geart", 11, "Financials"));

            messages.add(new SMSMessage("Yuval" , "Hello" , 100, "050-1234567", true));
            messages.add(new SMSMessage("Or" , "Good evening my friend" , 101, "052-9876543"));

        } catch (Exception e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Messaging System Menu: ");
            System.out.println("1. Add message");
            System.out.println("2. Delete message by ID");
            System.out.println("3. Print all message repository");
            System.out.println("4. Count messages containing specific words");
            System.out.println("5. Print digital messages only");
            System.out.println("6. Print all message previews (Extra option)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice < 1 || choice > 7) {
                    throw new IllegalArgumentException("Choice must be a number between 1 and 7.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Select message type (1: Board, 2: Email, 3: SMS): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid type input.");
                        scanner.next();
                        break;
                    }
                    int type = scanner.nextInt();
                    scanner.nextLine();        
                    try {
                        System.out.print("Enter sender: ");
                        String sender = scanner.nextLine();
                        System.out.print("Enter content: ");
                        String content = scanner.nextLine();
                        System.out.print("Enter message ID (integer): ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 

                        if (type == 1) {
                            System.out.print("Enter category: ");
                            String category = scanner.nextLine();
                            messages.add(new BoardMessage(sender, content, id, category));                            
                            System.out.println("Board message added successfully.");
                        } else if (type == 2) {
                            System.out.print("Enter email subject: ");
                            String subj = scanner.nextLine();
                            messages.add(new EmailMessage(sender, content, id, subj));
                            System.out.println("Email message added successfully.");
                        } else if (type == 3) {
                            System.out.print("Enter phone number: ");
                            String phone = scanner.nextLine();
                            messages.add(new SMSMessage(sender, content, id, phone));
                            System.out.println("SMS message added successfully.");
                        } else {
                            System.out.println("Invalid message type.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error adding message: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter message ID(integers) to remove: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Enter integer ID: ");
                        scanner.next();
                    }
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    
                    boolean removed = false;
                    for (int i = 0; i < messages.size(); i++) {
                        if (messages.get(i).getMessageId() == removeId) {
                            messages.remove(i);
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        System.out.println("Message deleted successfully.");
                    } else {
                        System.out.println("Message with ID " + removeId + " not found.");
                    }
                    break;

                case 3:
                    System.out.println("\n All Messages in Repository:");
                    if (messages.isEmpty()) {
                        System.out.println("Repository is empty.");
                    } else {
                        for (Message m : messages) {
                            System.out.println(m.toString());
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter words separated by space to search: ");
                    String line = scanner.nextLine();
                    String[] wordArr = line.split("\\s+");
                    ArrayList<String> searchWords = new ArrayList<>();
                    for (String w : wordArr) {
                        searchWords.add(w);
                    }
                    int count = 0;
                    for (Message m : messages) {
                        if (m.find(searchWords)) {
                            count++;
                        }
                    }
                    System.out.println("Number of messages matching words: " + count);
                    break;
                     
                case 5:
                    System.out.println("\nDigital Messages Only");
                    int digitalCount = 0;
                    for (Message m : messages) {
                        if (m instanceof IDigital) {
                            digitalCount++;
                            IDigital digital = (IDigital) m;
                            System.out.println(m.toString() + " | Communication: " + digital.printCommunicationMethod());
                        }
                    }
                    if (digitalCount == 0) {
                        System.out.println("No digital messages found.");
                    }
                    break;

                case 6:
                    System.out.println("\nMessage Previews");
                    for (Message m : messages) {
                        System.out.println(m.generatePreview());
                    }
                    break;

                case 7:
                    System.out.println("Exiting application. Goodbye!");
                    break;
            }
        } while (choice != 7);
        
        scanner.close();
    }
}