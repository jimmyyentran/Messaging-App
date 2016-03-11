import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class CardLayoutPanel implements ActionListener{
    private CardLayoutPanelListener listener;
    private static JPanel cards;
    private static CardLayout cardLayout = new CardLayout();
    private JButton removeC, removeB, newMessage, block;
    private static String contactUsername = "none";
    private static String blockUsername = "none";
    private static String chatId= null;
    private static JLabel contactLabel = new JLabel(contactUsername);
    private static JLabel blockLabel = new JLabel(blockUsername);
    private static JPanel contactPanel, blockPanel, messagePanel;
    private static JList messageList;
    private JTextField textField;
    private static JScrollBar scrollBar;
    private Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent e){
            String text = textField.getText();
            if(chatId != null){
                MessengerGui.getInstance().AddNewMessageToChat(text, chatId);
                messageList.setListData(MessengerGui.getInstance().GetAllMessagesInChat(chatId).toArray());
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        scrollBar.setValue(scrollBar.getMaximum());
                    }
                });
                textField.setText("");
            }else {
                throw new RuntimeException("Chat id not set");
            }
        }
    };

    public CardLayoutPanel() {
        removeC = new JButton("Remove");
        removeB = new JButton("Remove");
        newMessage = new JButton("New Message");

        removeC.addActionListener(this);
        newMessage.addActionListener(this);

        cards = new JPanel(cardLayout);
        messagePanel = makeMessagePanel();
        contactPanel = makeContactPanel();
        blockPanel = makeBlockPanel();

        cards.add(messagePanel, "messagePanel");
        cards.add(contactPanel, "contactPanel");
        cards.add(blockPanel, "blockPanel");


    }

    private JPanel makeContactPanel(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(contactLabel);
        panel.add(removeC);
        panel.add(newMessage);
        return panel;
    }

    private JPanel makeBlockPanel(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(blockLabel);
        panel.add(removeB);
        return panel;
    }

    private JPanel makeMessagePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //messageList
        Vector<String> vector = new Vector<String>();
        messageList = new JList();
        messageList.setCellRenderer(new MyCellRenderer(360));

        //scrollPane
        JScrollPane scrollPane = new JScrollPane(messageList);
        scrollBar = scrollPane.getVerticalScrollBar();

        //splitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, scrollPane, makeTextInputPanel());
        splitPane.setDividerLocation(200);
        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel makeTextInputPanel(){
        JPanel panel = new JPanel();

        JButton button = new JButton("Send");
        button.addActionListener(action);

        textField = new JTextField();
        textField.addActionListener(action);

        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.LINE_END);

        return panel;
    }

    public static void setContactUsername(String name){
        contactUsername = name;
        contactLabel.setText(name);
        contactLabel.repaint();
        cardLayout.show(cards, "contactPanel");
    }

    public static void setBlockUsername(String name){
        blockUsername = name;
        contactLabel.setText(name);
        contactPanel.repaint();
        cardLayout.show(cards, "blockPanel");
    }

    public static void setMessageList(String s){
        chatId = s;
        messageList.setListData(MessengerGui.getInstance().GetAllMessagesInChat(s).toArray());
        messageList.validate();
//        messageList.ensureIndexIsVisible(messageList.getMaxSelectionIndex());
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                scrollBar.setValue(scrollBar.getMaximum());
            }
        });
        scrollBar.setValue(scrollBar.getMaximum());
        cardLayout.show(cards, "messagePanel");
    }

    public JPanel getPanel() {
        return cards;
    }

    public void setListener(CardLayoutPanelListener listener){
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeC){
            System.out.println("Remove Contact button clicked");
            listener.removeContactEventOccurred(contactUsername);
        } else if (e.getSource() == removeB){
            System.out.println("Remove Blocked button clicked");
            listener.removeBlockEventOccurred(blockUsername);
        }else if (e.getSource() == newMessage){
            System.out.println("New Message clicked");
            listener.newMessageEventOccurred(contactUsername);
            cardLayout.show(cards, "messagePanel");
        }
    }
    class MyCellRenderer extends DefaultListCellRenderer {
        public static final String HTML_1 = "<html><body style='width: ";
        public static final String HTML_2 = "px'>";
        private int width;

        public MyCellRenderer(int width) {
            this.width = width;
        }

        private String htmlFormatterMessage(List<String> l){
            String html = HTML_1 + String.valueOf(width) + "\n" + HTML_2;
            html += String.format("%s %s<br>%s\n", l.get(3), l.get(2), l.get(1));
            html += "</html>";
            return html;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
//            String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
//                    + HTML_3;
//                    cellHasFocus);
            String text = htmlFormatterMessage((List<String>)value);
//            setText(value.toString());

            Component c = super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            if(index % 2 == 0) {
                c.setBackground(Color.pink);
            } else {
                c.setBackground(Color.white);
            }

//            return this;
            return c;
        }

//        public Component getListCellRendererComponent(JList list, List<String> value,
//                                                      int index, boolean isSelected, boolean cellHasFocus) {
////            String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
////                    + HTML_3;
////            return super.getListCellRendererComponent(list, text, index, isSelected,
////                    cellHasFocus);
//            String text = htmlFormatterMessage(value);
//            System.out.println(text);
////            if(index % 2 == 0) setBackground(Color.pink);
////            else setBackground(Color.white);
//
////            return this;
//            return super.getListCellRendererComponent(list, text, index, isSelected,
//                    cellHasFocus);
//        }

    }
}
