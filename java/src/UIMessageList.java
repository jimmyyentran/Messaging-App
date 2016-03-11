import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIMessageList extends UIAbstractList implements ActionListener
{
    private Map<String, List<List<String>>> messageList = new HashMap<String, List<List<String>>>();
    UIMessageList(){
        super();
    }

    protected void setList(){
        list = esql.ListMessages();
        System.out.println("Test");
        for(List<String> l : list){
            System.out.println("Test2");
            System.out.println(l.get(0));
            messageList.put(l.get(0), esql.GetAllMessagesInChat(l.get(0)));
            System.out.println("End");
        }
    }

    protected String htmlFormatter(int index){
        return null;
    }

    protected String htmlFormatterGroup(List<List<String>> l){
        String html = String.format("<html>%s", l.get(0).get(0).trim());
        for(int i = 1; i < l.size(); ++i){
            html += String.format("\n<br>%s", l.get(i).get(0).trim());
        }
        html += "</html>";
        System.out.println(html);
        return html;
    }

    protected String htmlFormatterMessage(List<String> l){
        String html = "<html>\n";
        html += String.format("%s %s<br>%s\n", l.get(3), l.get(2), l.get(1));
        html += "</html>";
        System.out.println(html);
        return html;
    }

    protected Vector<String> htmlFormatterMessageList(List<List<String>> l){
        Vector<String> v = new Vector<>();
        for(List<String> m : l){
            v.add(htmlFormatterMessage(m));
        }
        return v;
    }

    @Override
    public void loadButtons(){
        removeAll();
        setList();

//        for(List<String> tuple : list){
//            for(String attribute : tuple){
//                System.out.println(attribute);
//            }
//        }

        users = new JButton[list.size()];

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
            //problem with comparison so use ascii numbers
//            if(103 == ((int)list.get(i).get(1).charAt(0))){
                users[i].setText(htmlFormatterGroup(esql.AllUsersInChat(list.get(i).get(0))));
//            } else {
//                users[i].setText(list.get(i).get(2).trim());
//            }
        }

        for(JButton a : users){
            add(a);
            buttonGroup.add(a);
        }

        setAllActionListeners();
    }

    protected void customizeButton(JButton button){
    }

    @Override
    protected void setAllActionListeners() {
        for(JButton a: users){
            a.addActionListener(this);
        }
    }

//    public static void clickFirstButton(){
//    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        String chatId = list.get(Integer.parseInt(cmd)).get(0);
        CardLayoutPanel.setList(htmlFormatterMessageList(messageList.get(chatId)), chatId);
    }
}
