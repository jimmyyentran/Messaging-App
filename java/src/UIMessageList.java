import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class UIMessageList extends UIAbstractList implements ActionListener
{
    private Map<String, List<List<String>>> messageList = new HashMap<String, List<List<String>>>();
    UIMessageList(){
        super();
    }

    protected void setList(){
        list = esql.ListMessages();
        for(List<String> l : list){
            messageList.put(l.get(0), esql.GetAllMessagesInChat(l.get(0)));
        }
    }

    protected String htmlFormatter(int index){
        return null;
    }

    protected String htmlFormatterGroup(List<List<String>> l){
        String html = "<html>";
        int size = l.size();

        if(size <= 1){
            html += "<em>Private</em><br>";
        } else {
            html += "<em>Group</em><br>";
        }

        html += String.format("<b>%s</b>", l.get(0).get(0).trim());
            for (int i = 1; i < size; ++i) {
                html += String.format("\n& <b>%s</b>", l.get(i).get(0).trim());
        }
        html += "</html>";
//        System.out.println(html);
        return html;
    }

    protected String htmlFormatterMessage(List<String> l){
        String html = "<html>\n";
        html += String.format("<b>%s</b> %s<br>%s\n", l.get(3), l.get(2), l.get(1));
        html += "</html>";
//        System.out.println(html);
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

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
            users[i].setText(htmlFormatterGroup(esql.AllUsersInChat(list.get(i).get(0))));
            c.gridy = i;
//            gbl.setConstraints(users[i], c);
            add(users[i], c);
        }

//        for(int i = 0; i < list.size(); i++){
//            users[i] = makeUser(i);
            //problem with comparison so use ascii numbers
//            if(103 == ((int)list.get(i).get(1).charAt(0))){
//                users[i].setText(htmlFormatterGroup(esql.AllUsersInChat(list.get(i).get(0))));
//            } else {
//                users[i].setText(list.get(i).get(2).trim());
//            }
//        }

        for(JButton a : users){
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
        ((JButton)e.getSource()).setBackground(Color.cyan);
        String chatId = list.get(Integer.parseInt(cmd)).get(0);
//        CardLayoutPanel.setList(htmlFormatterMessageList(messageList.get(chatId)), chatId);
//        CardLayoutPanel.setMessageList(messageList.get(chatId), chatId);
        CardLayoutPanel.setMessageList(chatId);
    }
}
