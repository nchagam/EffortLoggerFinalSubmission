package application;



import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
 
//Encryption Prototype class - FH
public class EncryptionPrototype{
    private static final char ENCRYPTION_KEY = 'X';
    //private File encryptedFile;
    
    //Default Constructor
    public EncryptionPrototype()
    {
    	
    }
    
    //Method to encrypt the text using stored encryption key - FH
    public String encryptText(String text) {
        StringBuilder encryptedText = new StringBuilder(text.length());

        for (char c : text.toCharArray()) {
            // XOR encrypt each character with the encryption key
            char encryptedChar = (char)(c ^ ENCRYPTION_KEY);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }
    
    //Method to load a file and encrypt it - FH
    @SuppressWarnings("unused")
	private String loadAndDecryptText(File file) {
        StringBuilder decryptedText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                decryptedText.append(decryptText(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decryptedText.toString();
    }

    //Method to decrypt text, returning a substring with the expected string
    public String decryptText(String text) {
        return encryptText(text).substring(7); // XOR encryption is symmetric
    }

}