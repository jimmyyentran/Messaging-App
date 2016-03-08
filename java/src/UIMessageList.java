import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIMessageList extends UIAbstractList
{
    UIMessageList(){
        super();
    }

    protected void setList(){
        list = esql.ListMessages();
    }

    protected String htmlFormatter(int index){
        return null;
    }

    protected String htmlFormatterGroup(List<List<String>> l){
        String html = String.format("<html>%s", l.get(0).get(0).trim());
        System.out.println(l.size());
        for(int i = 1; i < l.size(); ++i){
            html += String.format("<br />%s", l.get(i).get(0).trim());
        }
        html += "</html>";
        System.out.println(html);
        return html;
    }

    @Override
    public void loadButtons(){
        removeAll();
        setList();

        for(List<String> tuple : list){
            for(String attribute : tuple){
                System.out.println(attribute);
            }
        }

        users = new JButton[list.size()];

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
            //problem with comparison so use ascii numbers
            if(103 == ((int)list.get(i).get(1).charAt(0))){
                users[i].setText(htmlFormatterGroup(esql.AllUsersInChat(list.get(i).get(0))));
            } else {
                users[i].setText(list.get(i).get(2).trim());
            }
        }

        for(JButton a : users){
            add(a);
        }
    }

    protected void customizeButton(JButton button){
    }
}
