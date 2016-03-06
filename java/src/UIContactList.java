import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIContactList extends JPanel implements ActionListener 
{
    // private UIContactList listener;
    // private JPanel contact_list;
    private JScrollPane scrollpane;
    private JButton[] users;

    UIContactList(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        users = new JButton[10];

        for(int i = 0; i < 10; i++){
            users[i] = makeUser();
        }

        for(JButton a : users){
            add(a);
        }
    }

    private JButton makeUser(){
        JButton button = new JButton();

        return button;
    }

    public void actionPerformed(ActionEvent e) {

    }

    // public void setRegistrationListener(UIListener listener) {
        // this.listener = listener;
    // }
}
