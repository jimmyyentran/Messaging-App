import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UITabbedPane extends JTabbedPane implements ActionListener 
{
    // private UITabbedPane listener;
    private UIContactList contact_list;
    private JPanel blocked_list;

    UITabbedPane(){
        contact_list = new UIContactList();
        blocked_list = new JPanel();
        addTab("Contact", contact_list);
        addTab("Blocked", blocked_list);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void loadUser(){
        contact_list.loadButtons();
    }

    // public void setRegistrationListener(UIListener listener) {
        // this.listener = listener;
    // }
}
