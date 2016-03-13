import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JOptionPane;

public class Login implements ActionListener{
    private JPanel panel;
    private LoginListener listener;
    private JButton loginButton, registerButton;
    private JTextField userText;
    private JPasswordField passwordText;

    public Login(){
        panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        userText.addActionListener(this);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        passwordText.addActionListener(this);
        panel.add(passwordText);

        loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

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
        } else if (e.getSource() == userText) {
            passwordText.requestFocusInWindow();
            cleanFields();
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

}
