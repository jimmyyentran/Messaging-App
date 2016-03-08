import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UI extends JPanel implements ActionListener 
{
    private UIListener listener;
    private UITabbedPane tabbedPane;
    private MessengerGui esql;
    private JSplitPane splitPane;
    private JPanel messageBoard;
    private JToolBar toolbar;
    private JButton logout, add, block, editStatus, deleteAccount;

    UI(){
        super(new BorderLayout());

        tabbedPane = new UITabbedPane();
        toolbar = new JToolBar();
        CardLayoutPanel cardLayoutPanel = new CardLayoutPanel();
        messageBoard = cardLayoutPanel.getPanel();
        splitPane =  new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, tabbedPane, messageBoard);
        esql = MessengerGui.getInstance();

        setUpToolbar();
        splitPane.setDividerLocation(150);

        add(toolbar, BorderLayout.PAGE_START);
        add(splitPane, BorderLayout.CENTER);
        // setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        // setLeftComponent(tabbedPane);
    }

    private void setUpToolbar(){
        setUpButtons();
        toolbar.add(logout);
        toolbar.add(add);
        toolbar.add(block);
        toolbar.add(editStatus);
        toolbar.add(deleteAccount);
    }

    private void setUpButtons(){
        logout = new JButton("logout");
        logout.setActionCommand("logout");
        logout.addActionListener(this);

        add = new JButton("add");
        add.setActionCommand("add");
        add.addActionListener(this);

        block = new JButton("block");
        block.addActionListener(this);

        editStatus = new JButton("editStaus");
        editStatus.addActionListener(this);

        deleteAccount = new JButton("deleteAccount");
        deleteAccount.addActionListener(this);
    }

    public void loadUser(){
        tabbedPane.loadUser();
    }


    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if("logout".equals(cmd)){
            listener.logoutEventOccured();
        }else if ("add".equals(cmd)){
            String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter username to add",
                    "Add user",
                    JOptionPane.PLAIN_MESSAGE
                    );
            if((s != null) && (s.length() > 0)){
                if(esql.CheckUser(s)){
                    try{
                        esql.AddToContact(s);
                        tabbedPane.loadUser();
                        tabbedPane.repaint();
                    }catch(Exception ev){
                        // JOptionPane.showMessageDialog(this, ev.getMessage());
                        JOptionPane.showMessageDialog(this, s + " is already a friend");
                    }
                }else {
                    JOptionPane.showMessageDialog(this, "No user with the name " + s);
                }
            }
        }else if ("block".equals(cmd)){
            String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter username to block",
                    "Block user",
                    JOptionPane.PLAIN_MESSAGE
                    );
            if((s != null) && (s.length() > 0)){
                if(esql.CheckUser(s)){
                    try{
                        esql.AddToBlock(s);
                        tabbedPane.loadUser();
                        tabbedPane.repaint();
                    }catch(Exception ev){
                        System.err.println (ev.getMessage ());
                        JOptionPane.showMessageDialog(this, s + " is already blocked");
                    }
                }else {
                    JOptionPane.showMessageDialog(this, "No user with the name " + s);
                }
            }
        }
    }

    public void setUIListener(UIListener listener) {
        this.listener = listener;
    }
}
