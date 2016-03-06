import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UITabbedPane extends JTabbedPane implements ActionListener 
{
    // private UITabbedPane listener;
    private JPanel contact_list;
    private JPanel blocked_list;

    UITabbedPane(){
        contact_list = new UIContactList();
        blocked_list = new JPanel();
        addTab("Contact", contact_list);
        addTab("Blocked", blocked_list);
    }

    public void actionPerformed(ActionEvent e) {

    }

    // public void setRegistrationListener(UIListener listener) {
        // this.listener = listener;
    // }
}
