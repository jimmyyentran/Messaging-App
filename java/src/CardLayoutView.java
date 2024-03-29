import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardLayoutView {
    private JPanel cards;
    private Registration registration;
    private Login login;
    private UI ui;
    private CardLayout cardLayout = new CardLayout();
    private MessengerGui esql;
    private String authorizedUser = null;

    public CardLayoutView() {
        // cards.setJ

        login = new Login();
        registration = new Registration();
        ui = new UI();

        esql = MessengerGui.getInstance();

        cards = new JPanel(cardLayout);

        cards.add(login.getPanel(), "login");
        cards.add(registration, "registration");
        cards.add(ui, "ui");

        login.getPanel().repaint();
        registration.repaint();

        setUpRegistration();
        setUpLogin();
        setUpUI();
    }

    public JPanel getPanel() {
        return cards;
    }

    private void setUpRegistration(){
        registration.setRegistrationListener(new RegistrationListener(){
            public void submitEventOccured(String login, String password, String phone){
                try{
                    esql.CreateUser(login, password, phone);
//                    List<List<String>> ret = esql.GetUser(login);
//                    esql.setUser(authorizedUser, ret.get(0).get(4), ret.get(0).get(5));
//                    ui.loadUser(authorizedUser);
                    JOptionPane.showMessageDialog(cards,"Congratulations, Account Created!");
                    registration.clearFields();
                    cardLayout.show(cards, "login");
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
                    List<List<String>> ret = esql.GetUser(authorizedUser);
                    System.out.println(ret);
                    esql.setUser(authorizedUser, ret.get(0).get(4), ret.get(0).get(5));
                    ui.loadUser(authorizedUser);
                    cardLayout.show(cards, "ui");
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

    private void setUpUI(){
        ui.setUIListener(new UIListener(){
            public void logoutEventOccured(){
                cardLayout.show(cards, "login");
                //change user
            }
            public void addEventOccured(){
                // String s = (String)JOptionPane.showInputDialog(
                        // cards,
                        // "Enter username to add",
                        // "Add user",
                        // JOptionPane.PLAIN_MESSAGE
                        // );
            }
            public void blockEventOccured(){}
            public void editStatusEventOccured(){}

            public void deleteAccountEventOccurred() {
                cardLayout.show(cards, "login");
            }
        });
    }
}
