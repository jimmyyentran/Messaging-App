import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public abstract class UIAbstractList extends JPanel
{
    // private UIContactList listener;
    // private JPanel contact_list;
    private UIAbstractListListener listener;
    private JScrollPane scrollpane;
    protected JButton[] users;
    protected MessengerGui esql;
    protected List<List<String>> list;
    protected ButtonGroup buttonGroup = new ButtonGroup();
    protected GridBagLayout gbl = new GridBagLayout();
    protected JTextField tf = new JTextField(5);
    protected JButton btn = new JButton();

    UIAbstractList(){
        setLayout(gbl);
        esql = MessengerGui.getInstance();
    }

    abstract protected void setList();
    abstract protected String htmlFormatter(int index);
    abstract protected void customizeButton(JButton button);
    abstract protected void setAllActionListeners();

    private void convertListToUsr(){

    };

    public void loadButtons(){
        removeAll();
        setList();

//        for(List<String> tuple : list){
//            for(String attribute : tuple){
//                System.out.println(attribute);
//            }
//        }

        users = new JButton[list.size()];

        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.BOTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
//        c.weighty = 1.0;

//        add(tf, c);
//        c.gridx = 1;
//
//        btn.setPreferredSize(new Dimension(20,20));
//        c.fill = GridBagConstraints.RELATIVE;
//        add(btn, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 2;
//        c.gridx = 0;

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
            c.gridy = i + 1;
            add(users[i], c);
//            gbl.setConstraints(users[i], c);
//            add(users[i]);
        }

        for(JButton a : users){
//            add(a);
            buttonGroup.add(a);
        }

        setAllActionListeners();

    }

    protected JButton makeUser(int index){
        JButton button = new JButton(htmlFormatter(index));
        button.setPreferredSize(new Dimension(120,80));
        // customizeButton(button);
        button.setActionCommand(Integer.toString(index));
        return button;
    }

//    public void actionPerformed(ActionEvent e) {
//        int index = Integer.parseInt(e.getActionCommand());
//    }

    public void setListener(UIAbstractListListener listener) {
        this.listener = listener;
    }
}

