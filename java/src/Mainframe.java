import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mainframe extends JFrame {
    private Login login;
    private CardLayoutView view;
    private MessengerGui model;
    public static final Image img = Toolkit.getDefaultToolkit().createImage("images/background.jpg");
    public static ImageIcon[] userImages = new ImageIcon[8];

    public Mainframe() {
        for(int i = 0; i < 8; i++){
            String imgUrl = String.format("images/user%d.png", i);
            userImages[i] = new ImageIcon(imgUrl);
        }
        login = new Login();
        view = new CardLayoutView();
        initUI();
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                // make sure to cleanup the created table and close the connection.
                try{
                    if(model != null) {
                        System.out.print("Disconnecting from database...");
                        model.cleanup ();
                        System.out.println("Done\n\nBye !");
                    }//end if
                }catch (Exception ev) {
                    // ignored.
                }//end try
            }
        });
    }

    private void initUI() {
        // add(login.getPanel());
        // add(new Registration());
        add(view.getPanel());
        setTitle("Messenger Application");
        // setSize(300, 150);
        setSize(650, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The main execution method
     *
     * @param args the command line arguments this inclues the <mysql|pgsql> <login file>
     */
    public static void main (String[] args) {
        if (args.length != 3) {
            System.err.println (
                    "Usage: " +
                    "java [-classpath <classpath>] " +
                    MessengerGui.class.getName () +
                    " <dbname> <port> <user>");
            return;
        }//end if

        // Greeting();
        try{
            // use postgres JDBC driver.
            Class.forName ("org.postgresql.Driver").newInstance ();
            // instantiate the MessengerGui object and creates a physical
            // connection.
            final String dbname = args[0];
            final String dbport = args[1];
            final String user = args[2];

            boolean keepon = true;

            //START GUI HERE
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MessengerGui esql = null;
                    try{
                        esql = new MessengerGui (dbname, dbport, user, "");
                    } catch(Exception e){
                        System.err.println (e.getMessage ());
                    }
                    Mainframe ex = new Mainframe();
                    ex.setVisible(true);
                }
            });

            // while(keepon) {
            // // These are sample SQL statements
            // System.out.println("MAIN MENU");
            // System.out.println("---------");
            // System.out.println("1. Create user");
            // System.out.println("2. Log in");
            // System.out.println("9. < EXIT");
            // String authorisedUser = null;
            // switch (readChoice()){
            // case 1: CreateUser(esql); break;
            // case 2: authorisedUser = LogIn(esql); break;
            // case 9: keepon = false; break;
            // default : System.out.println("Unrecognized choice!"); break;
            // }//end switch
            // if (authorisedUser != null) {
            // boolean usermenu = true;
            // while(usermenu) {
            // System.out.println("MAIN MENU");
            // System.out.println("---------");
            // System.out.println("1. Add to contact list");
            // System.out.println("2. Browse contact list");
            // System.out.println("3. Write a new message");
            // System.out.println(".........................");
            // System.out.println("9. Log out");
            // switch (readChoice()){
            // case 1: AddToContact(esql); break;
            // case 2: ListContacts(esql); break;
            // case 3: NewMessage(esql); break;
            // case 9: usermenu = false; break;
            // default : System.out.println("Unrecognized choice!"); break;
            // }
            // }
            // }
            // }//end while
        }catch(Exception e) {
            System.err.println (e.getMessage ());
        }finally{
        }//end try
    }//end main
}
