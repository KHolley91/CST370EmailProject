package email;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

public class DeleteEmail extends Connect
{
    public void delete(int index) 
    {
        index--;
        try 
        {
            Store store;
            store = ConnectStore();

            Folder emailFolderForDeletion = store.getFolder("INBOX");
            emailFolderForDeletion.open(Folder.READ_WRITE);

            Message[] messages = emailFolderForDeletion.getMessages();
            Message message = messages[index];
            message.setFlag(Flags.Flag.DELETED, true);

            emailFolderForDeletion.close(true);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
