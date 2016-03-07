import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIContactList extends JPanel implements ActionListener 
{
    // private UIContactList listener;
    // private JPanel contact_list;
    private JScrollPane scrollpane;
    private JButton[] users;
    private MessengerGui esql;
    private List<List<String>> contactList;

    UIContactList(){
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(0,1));
        esql = MessengerGui.getInstance();
    }

    public void loadButtons(){
        contactList = esql.ListContacts();

        for(List<String> tuple : contactList){
            for(String attribute : tuple){
                System.out.println(attribute);
            }
        }

        users = new JButton[contactList.size()];

        for(int i = 0; i < contactList.size(); i++){
            users[i] = makeUser(i);
        }

        for(JButton a : users){
            add(a);
        }
    }

    private JButton makeUser(int index){
        List<String> tuple = contactList.get(index);
        String htmlFormat = String.format("<html>%s<br />%s</html>", tuple.get(0), tuple.get(1));
        JButton button = new JButton(htmlFormat);
        return button;
    }

    public void actionPerformed(ActionEvent e) {

    }

    // public void setRegistrationListener(UIListener listener) {
    // this.listener = listener;
    // }
}
