import javax.swing.JFrame;

public class Mainframe extends JFrame {
    private Login login;

    public Mainframe() {
        login = new Login();
        initUI();
    }

    private void initUI() {
        add(login.getPanel());
        setTitle("Simple example");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
