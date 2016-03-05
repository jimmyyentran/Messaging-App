import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutView {
    private JPanel cards;
    private Registration registration;
    private Login login;
    private UI ui;
    private CardLayout cardLayout = new CardLayout();
    private MessengerGui esql;
    private String authorizedUser = null;

    public CardLayoutView(MessengerGui e) {
        // cards.setJ

        login = new Login();
        registration = new Registration();
        ui = new UI();

        esql = e;

        cards = new JPanel(cardLayout);
        cards.add(ui, "ui");
        cards.add(login.getPanel(), "login");
        cards.add(registration, "registration");

        setUpRegistration();
        setUpLogin();
    }

    public JPanel getPanel() {
        return cards;
    }

    private void setUpRegistration(){
        registration.setRegistrationListener(new RegistrationListener(){
            public void submitEventOccured(String login, String password, String phone){
                try{
                    esql.CreateUser(login, password, phone);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(cards, e.getMessage());
                }
            }

            public void cancelEventOccured(){
                cardLayout.show(cards, "login");
            }
        });
    }

    private void setUpLogin(){
        login.setLoginListener(new LoginListener(){
            public void loginEventOccured(String login, String password){
                authorizedUser = esql.LogIn(login, password);
                if(authorizedUser != null){
                    //show new view here
                }
                else {
                    JOptionPane.showMessageDialog(cards, "Wrong Login Info");
                }
            }
            public void registrationEventOccured(){
                cardLayout.show(cards, "registration");
            }
        });
    }
}
