/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tumelo Mahape st10464247
 */
public class MessageTest {

    // TEST 1: MESSAGE LENGTH CHECKS (
   @Test
    public void testValidateAndSetMessageBody_Success() {
        Message msg = new Message();
        String result = msg.validateAndSetMessageBody("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testCheckRecipientCell_Success() {
        Message msg = new Message();
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    
    @Test
    public void testFindLongestMessage() {
        Message engine = new Message();
        
        // Mock data block representing populated message content strings
        String[] mockMessages = {
            "Short text", 
            "This is the absolute longest message in the parallel array system tracking payload.", 
            "Medium length text message string."
        };
        
        String longest = engine.findLongestMessage(mockMessages);
        assertEquals("This is the absolute longest message in the parallel array system tracking payload.", longest);
    }

    @Test
    public void testSearchByMessageID_Found() {
        Message engine = new Message();
        
        // Setup mock aligned parallel array data rows
        String[] mockIDs = {"1001", "1002", "1003"};
        String[] mockRecipients = {"+27710000001", "+27710000002", "+27710000003"};
        String[] mockMessages = {"Hello first", "Target message content text", "Hello third"};
        
        String result = engine.searchByMessageID("1002", mockIDs, mockRecipients, mockMessages);
        assertEquals("Recipient: +27710000002 | Message: Target message content text", result);
    }

    @Test
    public void testSearchByMessageID_NotFound() {
        Message engine = new Message();
        String[] mockIDs = {"1001", "1002"};
        String[] mockRecipients = {"+27710000001", "+27710000002"};
        String[] mockMessages = {"Msg 1", "Msg 2"};
        
        String result = engine.searchByMessageID("9999", mockIDs, mockRecipients, mockMessages);
        assertEquals("Message ID not found.", result);
    }

    @Test
    public void testCountMessagesToRecipient() {
        Message engine = new Message();
        
        // Target tracking recipient has multiple records inside the row arrays
        String[] mockRecipients = {"+27718693002", "+27700000000", "+27718693002", "+27755555555"};
        
        int matchCount = engine.countMessagesToRecipient("+27718693002", mockRecipients);
        assertEquals(2, matchCount); // Should accurately count the duplicate entries
    }

    @Test
    public void testDeleteByHash_Success() {
        Message engine = new Message();
        
        String[] mockHashes = {"48:0:HITONIGHT", "12:1:TESTHASH", "99:2:FINALWORD"};
        String[] mockMessages = {"Msg 1", "Message to delete", "Msg 3"};
        String[] mockIDs = {"101", "102", "103"};
        
        // Attempt execution wipe operation using correct uppercase key sequence matching
        boolean isDeleted = engine.deleteByHash("12:1:TESTHASH", mockHashes, mockMessages, mockIDs);
        
        assertTrue(isDeleted);
        assertNull(mockMessages[1]); // Ensure data row reference was successfully cleared out to null
        assertNull(mockHashes[1]);
    }
}
