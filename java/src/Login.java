import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Login implements ActionListener{
    private JPanel panel;
    private LoginListener listener;
    private JButton loginButton, registerButton;
    private JTextField userText;
    private JPasswordField passwordText;

    public Login(){
        panel = new BackgroundPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        JLabel userLabel = new JLabel("User");
//        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        userText = new JTextField(10);
//        userText.setBounds(100, 10, 160, 25);
        userText.addActionListener(this);
        panel.add(userText,c);

        JLabel passwordLabel = new JLabel("Password");
//        passwordLabel.setBounds(10, 40, 80, 25);
        c.gridx= 0;
        c.gridy= 1;
        panel.add(passwordLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        passwordText = new JPasswordField(10);
//        passwordText.setBounds(100, 40, 160, 25);
        passwordText.addActionListener(this);
        panel.add(passwordText, c);

        c.gridx = 0;
        c.gridy = 2;
        loginButton = new JButton("login");
//        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton, c);

        c.gridx = 1;
        c.gridy = 2;
        registerButton = new JButton("register");
//        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton, c);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            listener.loginEventOccured(userText.getText(), String.valueOf(passwordText.getPassword()));
            cleanFields();
        } else if (e.getSource() == registerButton){
            listener.registrationEventOccured();
            cleanFields();
        } else if (e.getSource() == passwordText){
            listener.loginEventOccured(userText.getText(), String.valueOf(passwordText.getPassword()));
            cleanFields();
        } else if (e.getSource() == userText) {
            passwordText.requestFocusInWindow();
        }
    }

    private void cleanFields(){
        userText.setText("");
        passwordText.setText("");
    }

    public JPanel getPanel(){
        return panel;
    }

    public void setLoginListener(LoginListener listener){
        this.listener = listener;
    }

    class BackgroundPanel extends JPanel{
//        Image img;
        public BackgroundPanel(){
//            System.out.println("BACKGROUNDPANEL");
//            img = Toolkit.getDefaultToolkit().createImage("images/background.jpg");
        }
        @Override
        public void paintComponent(Graphics g){
            g.drawImage(Mainframe.img, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
