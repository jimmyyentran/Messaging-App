import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutPanel {
    private JPanel cards;
    private CardLayout cardLayout = new CardLayout();
    private JButton remove, newMessage, block;
    private static String contactUsername = "none";
    private static JLabel user = new JLabel(contactUsername);
    private static JPanel contactPanel, blockPanel;

    public CardLayoutPanel() {
        remove = new JButton("Remove");
        newMessage = new JButton("New Message");
        cards = new JPanel(cardLayout);
        contactPanel = makeContactPanel();
        cards.add(contactPanel, "panel");
    }

    private JPanel makeContactPanel(){
        JPanel panel = new JPanel();
        panel.add(user);
        panel.add(remove);
        panel.add(newMessage);
        return panel;
    }

    public static void setContactUsername(String name){
        contactUsername = name;
        user.setText(name);
        contactPanel.repaint();
    }

    public JPanel getPanel() {
        return cards;
    }

}
