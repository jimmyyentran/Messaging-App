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
        addTab("M", new JScrollPane(message_list));
        addTab("C", new JScrollPane(contact_list));
        addTab("B", new JScrollPane(blocked_list));
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void loadUser(){
        contact_list.loadButtons();
        blocked_list.loadButtons();
        message_list.loadButtons();
    }

    // public void setRegistrationListener(UIListener listener) {
        // this.listener = listener;
    // }
}
