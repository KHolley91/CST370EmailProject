package email;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

public class NewFolder extends Connect
{   
    boolean isCreated = true; 
    
    void NewFolder(String fname)
    {
        try
        {       
            //Get connection
            Store store;
            store = ConnectStore();
            
            Folder[] f = store.getDefaultFolder().list();
            for(Folder fd:f)
            {
                System.out.println(">> "+fd.getName());
            } 
            
            Folder defaultFolder = store.getFolder("INBOX");  
       
            Folder newFolder = defaultFolder.getFolder(fname);   
            isCreated = newFolder.create(Folder.HOLDS_MESSAGES);   
            System.out.println("created: " + isCreated);   

            f = store.getDefaultFolder().list();
            for(Folder fd:f)
            {
                System.out.println(">> "+fd.getName());
            }         
        }
        catch (NoSuchProviderException e) 
        {
            e.printStackTrace();
        }
        catch (MessagingException e)   
        {   
            System.out.println("Error creating folder: " + e.getMessage());   
            e.printStackTrace();   
            isCreated = false;   
        }     
    }
}