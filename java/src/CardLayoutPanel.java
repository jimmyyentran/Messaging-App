import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutPanel implements ActionListener{
    private CardLayoutPanelListener listener;
    private static JPanel cards;
    private static CardLayout cardLayout = new CardLayout();
    private JButton removeC, removeB, newMessage, block;
    private static String contactUsername = "none";
    private static String blockUsername = "none";
    private static JLabel contactLabel = new JLabel(contactUsername);
    private static JLabel blockLabel = new JLabel(blockUsername);
    private static JPanel contactPanel, blockPanel;

    public CardLayoutPanel() {
        removeC = new JButton("Remove");
        removeB = new JButton("Remove");
        newMessage = new JButton("New Message");

        removeC.addActionListener(this);
        newMessage.addActionListener(this);

        cards = new JPanel(cardLayout);
        contactPanel = makeContactPanel();
        blockPanel = makeBlockPanel();
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
        }
    }
}
