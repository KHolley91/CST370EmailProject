package email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail extends Connect
{
    static String filePath = null;
    static String fileName = null;
    
    void addFilePath(String path)
    {
        filePath = path;
    }
    void addFileName(String name)
    {
        fileName = name;
    }
    void SendEmail(String to, String sub, String msg) throws MessagingException 
    {
	try 
        {
            Session session = ConnectSession();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);
            
            if(fileName != null && filePath != null)
            {
                Multipart multipart = new MimeMultipart();
                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText(msg);
                MimeBodyPart attachmentBodyPart= new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(fileName); 
                multipart.addBodyPart(textBodyPart);
                multipart.addBodyPart(attachmentBodyPart);
                message.setContent(multipart);
                System.out.println("Attached!");
            }
            Transport.send(message);
            
            System.out.println("E-mail Sent.");
            fileName = null;
            filePath = null;
	} 
        catch (MessagingException e) 
        {
            throw new RuntimeException(e);
	}
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}