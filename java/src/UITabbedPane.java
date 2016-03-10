import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UITabbedPane extends JTabbedPane implements ActionListener 
{
    // private UITabbedPane listener;
    private UIContactList contact_list;
    private UIBlockList blocked_list;
    private UIMessageList message_list;

    UITabbedPane(){
        contact_list = new UIContactList();
        blocked_list = new UIBlockList();
        message_list = new UIMessageList();

//        java.net.URL block_list = UITabbedPane.class.getResource("images/blocklist_16.png");
        ImageIcon block_list = new ImageIcon("images/blocklist_16.png");
        ImageIcon contact_list_pic= new ImageIcon("images/contactlist_16.png");
        ImageIcon chat_list = new ImageIcon("images/chatlist_16.png");

        addTab(null , new JScrollPane(message_list));
        addTab(null, new JScrollPane(contact_list));
        addTab(null, new JScrollPane(blocked_list));

        setIconAt(0, chat_list);
        setIconAt(1, contact_list_pic);
        setIconAt(2, block_list);

    }

    public void actionPerformed(ActionEvent e) {

    }

    public void loadUser(){
        contact_list.loadButtons();
        blocked_list.loadButtons();
        message_list.loadButtons();
    }

    public void reloadContacts(){
        contact_list.loadButtons();
    }

    public void reloadBlocked() {
        blocked_list.loadButtons();
    }

    // public void setRegistrationListener(UIListener listener) {
        // this.listener = listener;
    // }
}
