import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;

public class UI extends JPanel implements ActionListener 
{
    private UIListener listener;
    private UITabbedPane tabbedPane;
    private MessengerGui esql;
    private JSplitPane splitPane;
    private JPanel messageBoard;
    private JMenuBar menuBar;
    private JMenuItem logout, add, block, editStatus, deleteAccount, removeChat, addChat;
    private JToolBar toolbar;
//    private JButton logout, add, block, editStatus, deleteAccount, removeChat;

    UI(){
        super(new BorderLayout());

        toolbar = new JToolBar();
        menuBar = new JMenuBar();
        CardLayoutPanel cardLayoutPanel = new CardLayoutPanel();
        cardLayoutPanel.setListener(cardLayoutPanelListener);
        messageBoard = cardLayoutPanel.getPanel();
        tabbedPane = new UITabbedPane();
        splitPane =  new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, tabbedPane, messageBoard);
        esql = MessengerGui.getInstance();

//        setUpToolbar();
        setUpMenubar();
        splitPane.setDividerLocation(220);

        add(menuBar, BorderLayout.PAGE_START);
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
        toolbar.add(removeChat);
    }

    private void setUpMenubar(){
        JMenu account = new JMenu("Account");
        logout = new JMenuItem("Logout");
        logout.setActionCommand("logout");
        logout.addActionListener(this);
        account.add(logout);

        deleteAccount = new JMenuItem("Delete Account");
        deleteAccount.setActionCommand("deleteAccount");
        deleteAccount.addActionListener(this);
        account.add(deleteAccount);

        JMenu contacts = new JMenu("Contacts");
        add = new JMenuItem("Add User");
        add.setActionCommand("add");
        add.addActionListener(this);
        contacts.add(add);

        block = new JMenuItem("Block User");
        block.setActionCommand("block");
        block.addActionListener(this);
        contacts.add(block);

//        editStatus = new JMenuItem("editStaus");
//        editStatus.addActionListener(this);

        JMenu chats =  new JMenu("Chats");
        addChat = new JMenuItem("Add Chat");
        addChat.setActionCommand("addChat");
        addChat.addActionListener(this);
        chats.add(addChat);

        removeChat = new JMenuItem("Remove Chat");
        removeChat.setActionCommand("removeChat");
        removeChat.addActionListener(this);
        chats.add(removeChat);

        menuBar.add(account);
        menuBar.add(contacts);
        menuBar.add(chats);
    }

    private void setUpButtons(){
//        logout = new JButton("logout");
//        logout.setActionCommand("logout");
//        logout.addActionListener(this);
//
//        add = new JButton("add");
//        add.setActionCommand("add");
//        add.addActionListener(this);
//
//        block = new JButton("block");
//        block.addActionListener(this);
//
//        editStatus = new JButton("editStaus");
//        editStatus.addActionListener(this);
//
//        deleteAccount = new JButton("deleteAccount");
//        deleteAccount.addActionListener(this);
//
//        removeChat = new JButton("removeChat");
//        removeChat.setActionCommand("removeChat");
//        removeChat.addActionListener(this);
    }

    public void loadUser(){
        tabbedPane.loadUser();
    }

    private CardLayoutPanelListener cardLayoutPanelListener = new CardLayoutPanelListener() {
        @Override
        public void removeContactEventOccurred(String s) {
            esql.RemoveFromContact(s);
            tabbedPane.reloadContacts();
            tabbedPane.repaint();
        }
        @Override
        public void removeBlockEventOccurred(String s) {
            esql.RemoveFromBlocked(s);
            tabbedPane.reloadBlocked();
            tabbedPane.repaint();
        }

        @Override
        public void newMessageEventOccurred(String s) {
            String str = esql.GetPrivateChat(s);
            if(str == null){
                //no private messages, so add
                int chat_id = 0;
                try {
                    chat_id = esql.AddNewPrivateChat(s);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(UI.this, e.getMessage());
                    return;
                }
//                CardLayoutPanel.setChatId(s);
                CardLayoutPanel.setMessageList(Integer.toString(chat_id));
                tabbedPane.reloadMessages();
                tabbedPane.repaint();
            }else {
                CardLayoutPanel.setMessageList(str);
            }
        }

        @Override
        public void addUserToChatEventOccurred(String s) {
            if(esql.IsInitSender(s)) {
                String str = (String) JOptionPane.showInputDialog(
                        UI.this,
                        "Enter username to add to chat",
                        "Add user",
                        JOptionPane.PLAIN_MESSAGE
                );
                if ((str != null) && (str.length() > 0)) {
                        if (!esql.GetUser(str).isEmpty()) {
                            try {
                                esql.AddUserToChat(s, str);
                                tabbedPane.loadUser();
                                tabbedPane.repaint();
                            } catch(SQLException ex){
                                JOptionPane.showMessageDialog(UI.this, "User is already added!");
                            }
                            catch (Exception ev) {
                                JOptionPane.showMessageDialog(UI.this, ev.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(UI.this, "No user with the name " + str);
                        }
                }
            }else {
                JOptionPane.showMessageDialog(UI.this, "You are not the initial user!");
            }
        }

        @Override
        public void removeUserFromChatEventOccurred(String s) {
            if(esql.IsInitSender(s)) {
                List<List<String>> tmp = esql.AllUsersInChat(s);
                String[] userNames = new String[tmp.size()];
                for(int i = 0; i < tmp.size(); i++){
                    userNames[i] = tmp.get(i).get(0);
                }
                String str = (String) JOptionPane.showInputDialog(
                        UI.this,
                        "Remove User from Chat",
                        "Remove User",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        userNames,
                        userNames[0]
                );
                if ((str != null) && (str.length() > 0)) {
                    if (!esql.GetUser(str).isEmpty()) {
                        try {
                            esql.RemoveUserFromChat(s, str);
                            tabbedPane.loadUser();
                            tabbedPane.repaint();
                        } catch (Exception ev) {
                            JOptionPane.showMessageDialog(UI.this, ev.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(UI.this, "No user with the name " + str);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(UI.this, "You are not the initial user!");
            }
        }

    };

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if("logout".equals(cmd)){
            listener.logoutEventOccured();
            CardLayoutPanel.clearMessageList();
        }else if ("add".equals(cmd)){
            String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter username to add",
                    "Add user",
                    JOptionPane.PLAIN_MESSAGE
                    );
            if((s != null) && (s.length() > 0)){
                if(!esql.GetUser(s).isEmpty()){
                    try{
                        esql.AddToContact(s);
                        tabbedPane.reloadContacts();
                        tabbedPane.repaint();
                    }catch(Exception ev){
                        System.err.println(ev.getMessage());
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
                if(!esql.GetUser(s).isEmpty()){
                    if(!s.equals(esql.getUser())) {
                        try {
                            esql.AddToBlock(s);
                            tabbedPane.reloadBlocked();
                            tabbedPane.repaint();
                        } catch (Exception ev) {
                            System.err.println(ev.getMessage());
                            JOptionPane.showMessageDialog(this, s + " is already blocked");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "You cannot block yourself");
                    }
                }else {
                    JOptionPane.showMessageDialog(this, "No user with the name " + s);
                }
            }
        }else if ("removeChat".equals(cmd)){
            try{
                MessengerGui.getInstance().DeleteChat(CardLayoutPanel.getChatId());
                CardLayoutPanel.clearMessageList();
                tabbedPane.reloadMessages();
                tabbedPane.repaint();
                CardLayoutPanel.setChatId(null);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage());
            }
        }else if ("deleteAccount".equals(cmd)){
            try{
                MessengerGui.getInstance().DeleteUser();
                listener.deleteAccountEventOccurred();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage());
            }
        }else if ("addChat".equals(cmd)){
            String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Enter in username to start new chat",
                    "Start new chat",
                    JOptionPane.PLAIN_MESSAGE
            );
            if((s != null) && (s.length() > 0)){
                if(!esql.GetUser(s).isEmpty()){
                    String str = esql.GetPrivateChat(s);
                    if(str == null){
                        //no private messages, so add
                        int chat_id = 0;
                        try {
                            chat_id = esql.AddNewPrivateChat(s);
                        } catch (Exception ex){
                            JOptionPane.showMessageDialog(UI.this, ex.getMessage());
                            return;
                        }
//                CardLayoutPanel.setChatId(s);
                        CardLayoutPanel.setMessageList(Integer.toString(chat_id));
                        tabbedPane.reloadMessages();
                        tabbedPane.repaint();
                    }else {
                        CardLayoutPanel.setMessageList(str);
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
