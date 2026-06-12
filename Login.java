/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration1;

/**
 *
 * @author ST10464247 Tumelo MAHAPE
 */
public class Login {
    //Declearation 
     private String firstName;
    private String lastName;
      private String cellPhoneNumber;
    // variables for checking login
    private String storedUserName;
    private String storedPassword;
   
   
    public Login(String fname, String lname)        {
        this.firstName = fname;
        this.lastName = lname;
    }
   
    public boolean checkUserName(String username)   {
        return username.contains("_") && username.length() <= 5;
    }
   
    public boolean checkPasswordComplexity(String password) {
        //password must be at least 8 characters, one capital lettar, one number, one special characters eg(!@#$%^&*?<>)
        boolean passwordOkay = false;
        boolean hasNumber = false;
        boolean hasCap = false;
        boolean hasChar =false;
        char current;
       
        if (password.length() >= 8)             {
            for (int i = 0; i < password.length(); i++)     {
                current = password.charAt(i);
               
                if (Character.isUpperCase(current))         {
                    hasCap = true;
                   
                }
           
                if  (Character.isDigit(current))            {
                    hasNumber = true;
                }
               
                if (!(Character.isLetterOrDigit(current)))   {
                    hasChar = true;
                }
            }
            if (hasNumber && hasCap && hasChar)           {
                passwordOkay = true;
            }
            return passwordOkay;
        }
        return false;
    }
   
    public boolean checkCellPhoneNumber(String number)          {
        //regex for international number ( e.g. +27xxxxxxxxx)
        String regex = "^\\+\\d{11,13}$";
        return number.matches(regex);
       
    }
   
    public String registerUser(String userName, String password, String cellNumber, String fName, String lName)     {
        StringBuilder message = new StringBuilder();
        this.firstName = fName;
        this.lastName = lName;
           
    if (!checkUserName(userName))       {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }
   
    message.append("username successfully captured.\n");
   
    if (!checkPasswordComplexity(password))     {
        return " Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and special character. ";
    }
    message.append("Password successfully captured.\n");
   
    if (!checkCellPhoneNumber(cellNumber))         {
        return "Cell phone number incorrectly formatted or does not contain international code.";
    }
    message.append("Cell phone number successfully added.\n");
   
    this.storedUserName = userName;
    this.storedPassword = password;
    this.cellPhoneNumber = cellNumber;
   
    System.out.println(message.toString());
   
    return "Registration successful.";
   
}

public boolean loginUser(String username, String password)      {
    return username.equals(this.storedUserName) && password.equals(this.storedPassword);
}

public String returnLoginStatus(String userName, String password)   {
   if (loginUser(userName, password))       {
       
       // here I trigger the messaging application because the login was successful
       runQuickChatMessagingSystem();
       
        return "Welcome "  + this.firstName + " " + this.lastName + ", it is great to see you again.";
   } else {
       return "Username or password incorrect, please try again." ;
      
}
   
   }
 
//this is where the code of part 2 starts, continuation of part 1 
public static void runQuickChatMessagingSystem() {
    java.util.Scanner input = new java.util.Scanner(System.in);
    
    System.out.println("Welcome to QuickChat.");
    System.out.println("-------------------------");

    System.out.print("Enter total number of messages you wish to process: ");
    int maxMessages = input.nextInt();
    input.nextLine(); // Clear buffer
    
    // PART 3 PARALLEL ARRAYS INITIALIZATION
    String[] messageIDs = new String[maxMessages];
    String[] messageHashes = new String[maxMessages];
    String[] recipients = new String[maxMessages];
    String[] messages = new String[maxMessages];
    String[] storedMessagesJSON = new String[maxMessages];

    int totalMessagesSentCount = 0;
    int sequentialMessageIndex = 0; 
    boolean systemRunning = true;

    while (systemRunning) {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("Option 1) Send Messages");
        System.out.println("Option 2) Show recently sent messages");
        System.out.println("Option 3) Quit");
        System.out.print("Select an option: ");
        
        int mainMenuChoice = input.nextInt();
        input.nextLine(); // Clear buffer

        switch (mainMenuChoice) {
            case 1:
                if (sequentialMessageIndex >= maxMessages) {
                    System.out.println("Limit reached. You can only enter the preset " + maxMessages + " messages.");
                    break;
                }

                // Calling  the Message class 
                Message msgObj = new Message();
                System.out.println("\n[System Info] Message ID generated: " + msgObj.getMessageID());

                // Recipient Number Loop
                while (true) {
                    System.out.print("Enter Recipient Number (e.g., +27718693002): ");
                    String cellInput = input.nextLine();
                    String verificationResult = msgObj.checkRecipientCell(cellInput);
                    System.out.println(verificationResult);
                    if (verificationResult.contains("successfully captured")) break;
                }

                //  Message Body Loop
                while (true) {
                    System.out.print("Enter message text (max 250 chars): ");
                    String bodyInput = input.nextLine();
                    String contentResult = msgObj.validateAndSetMessageBody(bodyInput);
                    if (contentResult.equals("Message ready to send.")) {
                        System.out.println("Message sent");
                        break;
                    } else {
                        System.out.println(contentResult);
                    }
                }

                //  Creating the unique hash code pattern
                msgObj.createMessageHash(sequentialMessageIndex);

                // 4. Processing Status Selection Options
                System.out.println("\nChoose action handling processing options:");
                System.out.println("1 - Send Message");
                System.out.println("2 - Disregard Message");
                System.out.println("3 - Store Message to send later");
                System.out.print("Choice: ");
                int processingAction = input.nextInt();
                input.nextLine();

                String statusFeedback = msgObj.sentMessage(processingAction);
                System.out.println(statusFeedback);

                // SAVE DATA INTO PARALLEL ARRAYS
                messageIDs[sequentialMessageIndex] = msgObj.getMessageID();
                messageHashes[sequentialMessageIndex] = msgObj.getMessageHash();
                recipients[sequentialMessageIndex] = msgObj.getRecipient();
                messages[sequentialMessageIndex] = msgObj.getMessageBody();
                
                if (processingAction == 3) {
                    storedMessagesJSON[sequentialMessageIndex] = msgObj.storeMessage();
                }
                if (processingAction == 1) {
                    totalMessagesSentCount++;
                }

                // Displaying summary layout blocks
                System.out.println("\n=== MESSAGE RECORD SUMMARY ===");
                System.out.println("Message ID:   " + msgObj.getMessageID());
                System.out.println("Message Hash: " + msgObj.getMessageHash());
                System.out.println("Recipient:    " + msgObj.getRecipient());
                System.out.println("Message:      " + msgObj.getMessageBody());
                System.out.println("==============================");

                sequentialMessageIndex++;
                break;

            case 2:
                //FULL PART 3 TASK REPORT MENU 
                System.out.println("\n--- TASK REPORT MENU ---");
                System.out.println("1) Show Full Message Log");
                System.out.println("2) Show Longest Message");
                System.out.println("3) Search for a Message by ID");
                System.out.println("4) Search for Messages by Recipient");
                System.out.println("5) Delete a Message by Hash");
                System.out.print("Choice: ");
                int reportChoice = input.nextInt();
                input.nextLine(); // Clear buffer
                
                if (reportChoice == 1) {
                    // Showing Full Log
                    System.out.println("\n--- FULL MESSAGE LOG ---");
                    boolean empty = true;
                    for(int i = 0; i < sequentialMessageIndex; i++) {
                        if (messageIDs[i] != null) {
                            System.out.println("ID: " + messageIDs[i] + " | Hash: " + messageHashes[i] + " | Recipient: " + recipients[i] + " | Msg: " + messages[i]);
                            empty = false;
                        }
                    }
                    if (empty) System.out.println("No messages found in the system.");
                } 
                else if (reportChoice == 2) {
                    //Finding and Showing Longest Message
                    String longestMsg = "";
                    String longestRecipient = "";
                    for(int i = 0; i < sequentialMessageIndex; i++) {
                        if (messages[i] != null && messages[i].length() > longestMsg.length()) {
                            longestMsg = messages[i];
                            longestRecipient = recipients[i];
                        }
                    }
                    if (!longestMsg.isEmpty()) {
                        System.out.println("\n--- LONGEST MESSAGE ---");
                        System.out.println("Recipient: " + longestRecipient);
                        System.out.println("Message: " + longestMsg);
                        System.out.println("Length: " + longestMsg.length() + " characters");
                    } else {
                        System.out.println("No messages found.");
                    }
                } 
                else if (reportChoice == 3) {
                    //Searching by Message ID
                    System.out.print("Enter Message ID to search: ");
                    String searchID = input.nextLine();
                    boolean found = false;
                    for(int i = 0; i < sequentialMessageIndex; i++) {
                        if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                            System.out.println("\n--- MESSAGE FOUND ---");
                            System.out.println("Recipient: " + recipients[i]);
                            System.out.println("Message: " + messages[i]);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Message ID not found.");
                } 
                else if (reportChoice == 4) {
                    //Searching by Recipient Number
                    System.out.print("Enter Recipient Cell Number to search: ");
                    String searchRecipient = input.nextLine();
                    boolean found = false;
                    System.out.println("\n--- MESSAGES TO " + searchRecipient + " ---");
                    for(int i = 0; i < sequentialMessageIndex; i++) {
                        if (recipients[i] != null && recipients[i].equals(searchRecipient)) {
                            System.out.println("- [" + messageIDs[i] + "]: " + messages[i]);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("No messages found for this recipient.");
                } 
                else if (reportChoice == 5) {
                    //Deleting by Hash
                    System.out.print("Enter Message Hash to delete: ");
                    String searchHash = input.nextLine();
                    boolean found = false;
                    for(int i = 0; i < sequentialMessageIndex; i++) {
                        if (messageHashes[i] != null && messageHashes[i].equalsIgnoreCase(searchHash)) {
                            System.out.println("Deleting message: " + messages[i]);
                            // Clearing data elements out of parallel arrays
                            messageIDs[i] = null;
                            messageHashes[i] = null;
                            recipients[i] = null;
                            messages[i] = null;
                            storedMessagesJSON[i] = null;
                            System.out.println("Message successfully deleted.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Message Hash not found.");
                }
                break;

            case 3:
                systemRunning = false;
                break;
        }
    }
    System.out.println("\nTotal absolute volume of messages sent: " + totalMessagesSentCount);
}
}

    

     


