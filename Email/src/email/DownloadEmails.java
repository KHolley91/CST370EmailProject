package email;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

public class DownloadEmails extends Connect
{
    public void DownloadEmails() 
    {
        try 
        {      
            //Get connection
            Store store;
            store = ConnectStore();    
            
            //Open Inbox folder
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages(); 
            
            //Write emails into a text file
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("Inbox.txt"), "utf-8"))) 
            {
                for (int i = 0, n = messages.length; i < n; i++) 
                {
                    Message message = messages[i];
                    
                    writer.write("---------------------------------\r\n");
                    writer.write("Email Number " + (i + 1) + "\r\n");
                    writer.write("Subject: " + message.getSubject() + "\r\n");
                    writer.write("From: " + message.getFrom()[0] + "\r\n");
                    writer.write("Text: " + message.getContent().toString() + "\r\n");
                }
                writer.write("---------------------------------\r\n");
            }           
            
            //Close email folder and connection.
            emailFolder.close(false);
            store.close();

        } 
        catch (NoSuchProviderException e) 
        {
            e.printStackTrace();
        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
