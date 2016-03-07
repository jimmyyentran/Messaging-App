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
    private JButton logout, add, block, editStatus;

    UI(){
        super(new BorderLayout());

        tabbedPane = new UITabbedPane();
        toolbar = new JToolBar();
        messageBoard = new JPanel();
        splitPane =  new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, tabbedPane, messageBoard);

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
    }

    private void setUpButtons(){
        logout = new JButton("logout");
        logout.setActionCommand("logout");
        logout.addActionListener(this);

        add = new JButton("add");
        add.addActionListener(this);

        block = new JButton("block");
        block.addActionListener(this);

        editStatus = new JButton("editStaus");
        editStatus.addActionListener(this);
    }

    public void loadUser(){
        tabbedPane.loadUser();
    }


    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if("logout".equals(cmd)){
            listener.logoutEventOccured();
        }
    }

    public void setUIListener(UIListener listener) {
        this.listener = listener;
    }
}
