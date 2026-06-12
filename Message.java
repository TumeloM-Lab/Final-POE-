/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration1;
import java.util.Random;
/**
 *
 * @author St10464247 Tumelo MAHAPE
 */
public class Message {
   //Variables
    private String messageID;
    private String recipient;
    private String messageBody;
    private String messageHash;

    // Constructor (Generates a random 10-digit ID when created)
    public Message() {
        long randNum = (long)(Math.random() * 9000000000L) + 1000000000L;
        this.messageID = String.valueOf(randNum);
    }

    // Methods required 
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell(String cellInput) {
        if (cellInput.startsWith("+") && cellInput.length() > 4) {
            this.recipient = cellInput;
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String validateAndSetMessageBody(String bodyInput) {
        if (bodyInput.length() <= 250) {
            this.messageBody = bodyInput;
            return "Message ready to send.";
        } else {
            int exceededBy = bodyInput.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
    }

    public String createMessageHash(int messageNumber) {
        String firstTwoID = this.messageID.substring(0, 2);
        
        // Finding first and last words
        String[] words = this.messageBody.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        
        this.messageHash = firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord;
        return this.messageHash;
    }

    public String sentMessage(int choice) {
        switch(choice) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete the message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid action.";
        }
    }

    // Research Task: Storing message as a JSON string
    public String storeMessage() {
        return "{\n" +
               "  \"messageID\": \"\"" + this.messageID + "\",\n" +
               "  \"recipient\": \"\"" + this.recipient + "\",\n" +
               "  \"messageHash\": \"\"" + this.messageHash + "\"\n" +
               "}";
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageBody() { return messageBody; }
    public String getMessageHash() { return messageHash; }
    
public String findLongestMessage(String[] messages) {
        String longest = "";
        for (String msg : messages) {
            if (msg != null && msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    //Searching for a Message by its exact ID
    public String searchByMessageID(String searchID, String[] ids, String[] recipients, String[] messages) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null && ids[i].equals(searchID)) {
                return "Recipient: " + recipients[i] + " | Message: " + messages[i];
            }
        }
        return "Message ID not found.";
    }

    // Searching All Messages for a specific Recipient
    public int countMessagesToRecipient(String targetRecipient, String[] recipients) {
        int count = 0;
        for (String rep : recipients) {
            if (rep != null && rep.equals(targetRecipient)) {
                count++;
            }
        }
        return count;
    }

    // Deleting a Message using its Unique Hash string flag
    public boolean deleteByHash(String searchHash, String[] hashes, String[] messages, String[] ids) {
        for (int i = 0; i < hashes.length; i++) {
            if (hashes[i] != null && hashes[i].equalsIgnoreCase(searchHash)) {
                messages[i] = null;
                hashes[i] = null;
                ids[i] = null;
                return true; // Successfully found and wiped
            }
        }
        return false; // Hash not found
    }
} 

    

