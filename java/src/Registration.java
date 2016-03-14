import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registration extends JPanel implements ActionListener 
{ 
    JLabel l1, l2, l4, l5, l8;
    JTextField tf1, tf7;
    JButton btn1, btn2, btn3;
    JPasswordField p1, p2;
    private RegistrationListener listener;
//    private Image img;

    Registration()
    {
        // if extends JFrame
        // setVisible(true);
        // setSize(700, 700);
        setLayout(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setTitle("Registration Form in Java");

        // Title
        l1 = new JLabel("Registration Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name:");
        l4 = new JLabel("Password:");
        l5 = new JLabel("Confirm Password:");
        l8 = new JLabel("Phone No:"); 
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p2 = new JPasswordField();
        tf7 = new JTextField();

        btn1 = new JButton("Submit");
        btn2 = new JButton("Clear");
        btn3 = new JButton("Cancel");

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        // Title and Labels
        l1.setBounds(80, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l4.setBounds(80, 110, 200, 30);
        l5.setBounds(80, 150, 200, 30);
        l8.setBounds(80, 190, 200, 30);

        // Text fields
        tf1.setBounds(300, 70, 200, 30);
        p1.setBounds(300, 110, 200, 30);
        p2.setBounds(300, 150, 200, 30);
        tf7.setBounds(300, 190, 200, 30);
        btn1.setBounds(50, 230, 100, 30);
        btn2.setBounds(170, 230, 100, 30);
        btn3.setBounds(450, 230, 100, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l4);
        add(p1);
        add(l5);
        add(p2);
        add(l8);
        add(tf7);
        add(btn1);
        add(btn2);
        add(btn3);
    }

    public void clearFields(){
        tf1.setText("");
        p1.setText("");
        p2.setText("");
        tf7.setText("");
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == btn1)
        {
            int x = 0;
            String s1 = tf1.getText();

            char[] s3 = p1.getPassword();
            char[] s4 = p2.getPassword(); 
            String s8 = new String(s3);
            String s9 = new String(s4);

            String s7 = tf7.getText();

            //Passwords match
            if(s1.equals(""))
            {
                JOptionPane.showMessageDialog(btn1, "Name cannot be blank");
            }
            else if(s8.equals("") || s9.equals("")){
                JOptionPane.showMessageDialog(btn1, "Password fields cannot be blank");
            }
            else if (!s8.equals(s9))
            {
                JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
            }
            else
            {
                listener.submitEventOccured(s1, s8, s7);
            } 
        } 
        else if(e.getSource() == btn2)
        {
            tf1.setText("");
            p1.setText("");
            p2.setText("");
            tf7.setText("");
        }
        else if (e.getSource() == btn3) {
            listener.cancelEventOccured();
        }
    }

    public void setRegistrationListener(RegistrationListener listener) {
        this.listener = listener;
    }

    @Override
    public void paintComponent(Graphics g){
//        System.out.println("Draw");
//        super.paintComponent(g);
        g.drawImage(Mainframe.img, 0, 0, getWidth(), getHeight(), this);
    }
}
