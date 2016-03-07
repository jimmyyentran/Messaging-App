import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

public abstract class UIAbstractList extends JPanel implements ActionListener 
{
    // private UIContactList listener;
    // private JPanel contact_list;
    private JScrollPane scrollpane;
    protected JButton[] users;
    protected MessengerGui esql;
    protected List<List<String>> list;

    UIAbstractList(){
        setLayout(new GridLayout(0,1));
        esql = MessengerGui.getInstance();
    }

    abstract protected void setList();
    abstract protected String htmlFormatter(int index);
    abstract protected void customizeButton(JButton button);

    public void loadButtons(){
        removeAll();
        setList();

        for(List<String> tuple : list){
            for(String attribute : tuple){
                System.out.println(attribute);
            }
        }

        users = new JButton[list.size()];

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
        }

        for(JButton a : users){
            add(a);
        }
    }

    protected JButton makeUser(int index){
        JButton button = new JButton(htmlFormatter(index));
        button.setPreferredSize(new Dimension(100,50));
        // customizeButton(button);
        return button;
    }

    public void actionPerformed(ActionEvent e) {

    }

    // public void setRegistrationListener(UIListener listener) {
    // this.listener = listener;
    // }
}
