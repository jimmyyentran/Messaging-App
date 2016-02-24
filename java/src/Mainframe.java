import javax.swing.JFrame;

public class Mainframe extends JFrame {
    private Login login;

    public Mainframe() {
        login = new Login();
        initUI();
    }

    private void initUI() {
        // add(login.getPanel());
        add(new Registration());
        setTitle("Messenger Application");
        // setSize(300, 150);
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
