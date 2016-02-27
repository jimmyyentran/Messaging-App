import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutView {
    private JPanel cards;
    private Registration registration;
    private Login login;
    private CardLayout cardLayout = new CardLayout();

    public CardLayoutView() {
        login = new Login();
        registration = new Registration();

        cards = new JPanel(cardLayout);
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
            public void submitEventOccured(){

            }

            public void cancelEventOccured(){
                cardLayout.show(cards, "login");
            }
        });
    }

    private void setUpLogin(){
        login.setLoginListener(new LoginListener(){
            public void loginEventOccured(){

            }
            public void registrationEventOccured(){
                cardLayout.show(cards, "registration");
            }
        });
    }
}
