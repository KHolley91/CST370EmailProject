package email;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class Connect 
{
    static String username = null;
    static String password = null;
    
    void addUsername(String uname)
    {
        username = uname;
    }
    void addPassword(String pass)
    {
        password = pass;
    }
    String getUsername()
    {
        return username;
    }
    Store ConnectStore() throws MessagingException
    {
        Store store;
        String host = "imap.gmail.com";
        
        Properties props = new Properties();
        props.put("mail.imap.auth", "true");
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.setProperty("mail.store.protocol", "imaps");

        System.out.println("Attempting to connect...");

        Session session = Session.getDefaultInstance(props, null);
        store = session.getStore("imaps");
        store.connect(host, username, password);
        
        System.out.println("Connected!");
        return store;
    }
    Session ConnectSession() throws MessagingException
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
        
        System.out.println("Attempting to connect...");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() 
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
            {
		return new javax.mail.PasswordAuthentication(username, password);
            }
	});

        System.out.println("Connected!");
        return session;
    }
}
